package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import ir.hitelecom.accounting.entities.stock.Size;
import ir.hitelecom.accounting.repositories.stock.ProductRepository;
import ir.hitelecom.accounting.repositories.stock.ProductSizeRepository;
import ir.hitelecom.accounting.repositories.stock.SizeRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SizeService extends BaseService {

    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Size> fetchAll(Size size) {
        return sizeRepository.search(size.getValue());
    }

    public Size saveOrUpdate(Size size) {
        if (sizeRepository.existsByValue(size.getValue())) {
            throw new RuntimeException(getErrorMessage("sizeExists"));
        }
        Size newSize = sizeRepository.save(size);
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            ProductSize productSize = new ProductSize();
            productSize.setProduct(product);
            productSize.setSize(newSize);
            productSize.setCount(0);
            productSizeRepository.save(productSize);
        });
        return newSize;
    }

    public void delete(Size size) {
        sizeRepository.delete(size);
    }
}
