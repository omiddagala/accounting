package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.repositories.UserRepository;
import ir.hitelecom.accounting.repositories.stock.ProductRepository;
import ir.hitelecom.accounting.repositories.stock.ProductSizeRepository;
import ir.hitelecom.accounting.services.BaseService;
import ir.hitelecom.accounting.services.UserService;
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
            productSize.setProduct(result);
            productSizeService.save(productSize);
        });
    }

    public List<Product> fetchAll(Product product) {
        User user = userRepository.findByUsername(getLoggedInUsername());
        return productRepository.search(product.getName(), product.getType(), user.getParent());
    }

    public void delete(Product product) {
        productSizeService.deleteByProduct(product);
        productRepository.delete(product);
    }
}
