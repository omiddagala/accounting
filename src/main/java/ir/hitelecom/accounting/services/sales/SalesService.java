package ir.hitelecom.accounting.services.sales;

import ir.hitelecom.accounting.dto.PageableDTO;
import ir.hitelecom.accounting.entities.sales.Sales;
import ir.hitelecom.accounting.repositories.UserRepository;
import ir.hitelecom.accounting.repositories.sales.SalesRepository;
import ir.hitelecom.accounting.repositories.stock.ProductSizeRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SalesService extends BaseService {
    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Sales> fetchAll(PageableDTO dto) {
        return salesRepository.findAll(getPageable(dto)).getContent();
    }

    public Sales saveOrUpdate(Sales sales) {
        sales.setProductSize(productSizeRepository.findByCode(sales.getProductCode()));
        sales.setUser(userRepository.findByUsername(getLoggedInUsername()));
        return salesRepository.save(sales);
    }

    public void delete(Sales sales) {
        salesRepository.delete(sales);
    }

}
