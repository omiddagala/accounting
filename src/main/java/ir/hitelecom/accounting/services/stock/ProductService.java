package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.dto.stock.ProductListDTO;
import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Group;
import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import ir.hitelecom.accounting.repositories.UserRepository;
import ir.hitelecom.accounting.repositories.stock.GroupRepository;
import ir.hitelecom.accounting.repositories.stock.ProductRepository;
import ir.hitelecom.accounting.repositories.stock.ProductSizeRepository;
import ir.hitelecom.accounting.repositories.stock.TimelineRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService extends BaseService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductSizeService productSizeService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TimelineRepository timelineRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;

    public Product findOne(Long id) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            Product product = result.get();
            product.setProductSizes(productSizeService.getSizes(id));
            return product;
        } else {
            return null;
        }
    }

    public void saveOrUpdate(Product product) {
        boolean isEdit = product.getId() != null;
        if (isEdit && !productRepository.findById(product.getId()).get().getName().equals(product.getName()) && productRepository.existsByName(product.getName()))
            throw new RuntimeException(getErrorMessage("nameExists"));
        if (!isEdit && productRepository.existsByName(product.getName()))
            throw new RuntimeException(getErrorMessage("nameExists"));
        User user = userRepository.findByUsername(getLoggedInUsername());
        product.setOwner(user.getParent());
        product.setUser(user);
        Product result = productRepository.save(product);
        Group group = null;
        if (!isEdit) {
            Optional<Group> o = groupRepository.findById(product.getGroup().getId());
            group = o.get();
            if (group.getFromCode() == null) {
                group.setFromCode(0L);
            }
        }
        Group finalGroup = group;
        product.getProductSizes().forEach(productSize -> {
            if (productSize.getCount() == null) {
                productSize.setCount(0);
            }
            productSize.setProduct(result);
            if (!isEdit) {
                while (productSizeRepository.existsByCode(finalGroup.getFromCode()))
                    finalGroup.setFromCode(finalGroup.getFromCode() + 1);
                productSize.setCode(finalGroup.getFromCode());
                finalGroup.setFromCode(finalGroup.getFromCode() + 1);
            }
            productSizeService.save(productSize);
        });

    }

    public List<Product> fetchAll(ProductListDTO product) {
        if (product.getId() == null) {
            User user = userRepository.findByUsername(getLoggedInUsername());
            if (!product.isOrder()) {
                return productRepository.search(product.getName(), product.getType(), user.getReservoir(), getPageable(product.getPageableDTO()));
            } else {
                return productRepository.searchForOrder(product.getName(), product.getType(), user.getParent(), getPageable(product.getPageableDTO()));
            }
        } else {
            List<ProductSize> byCode = productSizeRepository.findByCode(product.getId());
            List<Product> result = new ArrayList<>();
            if(byCode.get(0)!=null) {
                result.add(byCode.get(0).getProduct());
            }
            else{
            // #TODO search with id (wrong print)
                Optional<ProductSize> byId = productSizeRepository.findById(product.getId());
                if (byId.isPresent())
                    result.add(byId.get().getProduct());
            }
            return result;
        }
    }

    public void delete(Product product) {
        productSizeService.deleteByProduct(product);
        timelineRepository.deleteByProductSizeProduct(product);
        productRepository.delete(product);
    }

    public List<ProductSize> search(ProductSize dto) {
        return productSizeRepository.findByCode(dto.getId());
    }

    public ProductSize findByCode(Long id) {
        ProductSize productSize = productSizeRepository.findByReservoirAndCode(userRepository.findByUsername(getLoggedInUsername()).getReservoir(), id);
        // #TODO search with id (wrong print)
        if (productSize == null)
            productSize = productSizeRepository.findByReservoirAndId(userRepository.findByUsername(getLoggedInUsername()).getReservoir(), id);
        if (productSize == null)
            throw new NullPointerException("productNotFound");
        return productSize;
    }
}
