package ir.hitelecom.accounting.controllers.stock;

import ir.hitelecom.accounting.dto.IdDTO;
import ir.hitelecom.accounting.dto.ProductListDTO;
import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import ir.hitelecom.accounting.services.stock.ProductService;
import ir.hitelecom.accounting.services.stock.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shop/product")
public class ProductController {

    @Autowired
    private ProductSizeService productSizeService;
    @Autowired
    private ProductService productService;

    @PostMapping("/sizes")
    @ResponseBody
    public List<ProductSize> getSizes(@RequestBody IdDTO productId) {
        return productSizeService.getSizes(productId.getId());
    }

    @PostMapping("/fetch")
    @ResponseBody
    public Product findOne(@RequestBody IdDTO productId) {
        return productService.findOne(productId.getId());
    }

    @PostMapping("/save")
    @ResponseBody
    public void saveOrUpdate(@RequestBody Product product) {
        productService.saveOrUpdate(product);
    }

    @PostMapping("/list")
    @ResponseBody
    public List<Product> fetchAll(@RequestBody ProductListDTO product) {
        return productService.fetchAll(product);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody Product product) {
         productService.delete(product);
    }

    @PostMapping("/search")
    @ResponseBody
    public List<ProductSize> search(@RequestBody ProductSize dto) {
         return productService.search(dto);
    }

    @PostMapping("/findByCode")
    @ResponseBody
    public ProductSize findByCode(@RequestBody Long code) {
        return productService.findByCode(code);
    }
}
