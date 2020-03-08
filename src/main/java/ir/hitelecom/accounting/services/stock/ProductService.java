package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.repositories.stock.ProductRepository;
import ir.hitelecom.accounting.repositories.stock.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductSizeService productSizeService;

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
        Product result = productRepository.save(product);
        productSizeService.deleteByProduct(product);
        product.getProductSizes().forEach(productSize -> {
            productSize.setProduct(result);
            productSizeService.save(productSize);
        });
    }

    public List<Product> fetchAll(Product product) {
        return productRepository.search(product.getName(), product.getType());
    }

    public void delete(Product product) {
        productSizeService.deleteByProduct(product);
        productRepository.delete(product);
    }
}
