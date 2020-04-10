package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.dto.ProductListDTO;
import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Group;
import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import ir.hitelecom.accounting.repositories.UserRepository;
import ir.hitelecom.accounting.repositories.stock.GroupRepository;
import ir.hitelecom.accounting.repositories.stock.ProductRepository;
import ir.hitelecom.accounting.repositories.stock.TimelineRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        if (productRepository.existsByName(product.getName())) {
            throw new RuntimeException(getErrorMessage("nameExists"));
        }
        boolean isEdit = product.getId() != null;
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
            ProductSize productSize = productSizeService.findByCode(product.getId());
            List<Product> result = new ArrayList<Product>();
            result.add(productSize.getProduct());
            return result;
        }
    }

    public void delete(Product product) {
        productSizeService.deleteByProduct(product);
        timelineRepository.deleteByProductSizeProduct(product);
        productRepository.delete(product);
    }

    public List<ProductSize> search(ProductSize dto) {
        Map<Long, ProductSize> result = new HashMap<>();
        ProductSize o = productSizeService.findByCode(dto.getId());
        if (o == null) {
            o = productSizeService.findById(dto.getId());
        }
        if (o != null) {
            ProductSize p = o;
            List<ProductSize> fetched = productSizeService.findProductByProductNameAndProductOwner(p.getProduct().getName(), p.getProduct().getOwner());
            fetched.forEach(f -> {
                result.putIfAbsent(f.getProduct().getId(), f);
            });
        }
        return new ArrayList<>(result.values());
    }

    public ProductSize findByCode(Long id){
        return productSizeService.findByCode(id);
    }
}
