package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import ir.hitelecom.accounting.repositories.UserRepository;
import ir.hitelecom.accounting.repositories.stock.ProductRepository;
import ir.hitelecom.accounting.repositories.stock.ProductSizeRepository;
import ir.hitelecom.accounting.services.BaseService;
import ir.hitelecom.accounting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService extends BaseService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductSizeService productSizeService;
    @Autowired
    private UserRepository userRepository;

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
        User user = userRepository.findByUsername(getLoggedInUsername());
        product.setOwner(user.getParent());
        Product result = productRepository.save(product);
        productSizeService.deleteByProduct(product);
        product.getProductSizes().forEach(productSize -> {
            if (productSize.getCount() == null) {
                productSize.setCount(0);
            }
            productSize.setProduct(result);
            productSizeService.save(productSize);
        });

    }

    public List<Product> fetchAll(Product product) {
        if (product.getId() == null) {
            User user = userRepository.findByUsername(getLoggedInUsername());
            return productRepository.search(product.getName(), product.getType(), user.getParent());
        } else {
            Optional<ProductSize> productSize = productSizeService.findById(product.getId());
            List<Product> result = new ArrayList<Product>();
            productSize.ifPresent(size -> result.add(size.getProduct()));
            return result;
        }
    }

    public void delete(Product product) {
        productSizeService.deleteByProduct(product);
        productRepository.delete(product);
    }

    public List<ProductSize> search(ProductSize dto) {
        Map<Long, ProductSize> result = new HashMap<>();
        Optional<ProductSize> o = productSizeService.findById(dto.getId());
        if (o.isPresent()) {
            ProductSize p = o.get();
            List<ProductSize> fetched = productSizeService.findProductByProductNameAndProductOwner(p.getProduct().getName(), p.getProduct().getOwner());
            fetched.forEach(f -> {
                result.putIfAbsent(f.getProduct().getId(), f);
            });
        }
        return new ArrayList<>(result.values());
    }
}
