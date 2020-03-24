package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import ir.hitelecom.accounting.entities.stock.Reservoir;
import ir.hitelecom.accounting.entities.stock.Size;
import ir.hitelecom.accounting.repositories.stock.ProductSizeRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductSizeService extends BaseService {

    @Autowired
    private ProductSizeRepository productSizeRepository;


    public List<ProductSize> getSizes(Long productId) {
        List<ProductSize> result = new ArrayList<>();
        List<Object[]> list = productSizeRepository.getSizes(productId != null ? productId : 0);
        list.forEach(elem -> {
            ProductSize productSize = new ProductSize();
            productSize.setId(elem[3] == null ? null : ((BigInteger) elem[3]).longValue());
            Product product = new Product();
            product.setId(elem[5] == null ? null : ((BigInteger) elem[3]).longValue());
            Reservoir reservoir = new Reservoir();
            reservoir.setId(elem[6] == null ? null : ((BigInteger) elem[3]).longValue());
            reservoir.setName((String) elem[4]);
            product.setReservoir(reservoir);
            productSize.setProduct(product);
            Size size = new Size();
            size.setId(((BigInteger) elem[0]).longValue());
            size.setValue((String) elem[1]);
            productSize.setSize(size);
            productSize.setCount((Integer) elem[2]);
            productSize.setCode(elem[7] == null ? null : ((BigInteger) elem[7]).longValue());
            result.add(productSize);
        });
        return result;
    }

    public void deleteByProduct(Product product) {
        productSizeRepository.deleteByProduct(product);
    }

    public void save(ProductSize productSize) {
        productSizeRepository.save(productSize);
    }

    public ProductSize findByCode(Long id) {
        return productSizeRepository.findByCode(id);
    }

    public List<ProductSize> findProductByProductNameAndProductOwner(String name, User owner) {
        return productSizeRepository.findProductByProductNameAndProductOwner(name, owner);
    }

    public void deleteAll(List<ProductSize> productSizes) {
        productSizeRepository.deleteAll(productSizes);
    }
}
