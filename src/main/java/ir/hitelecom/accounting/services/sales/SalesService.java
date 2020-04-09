package ir.hitelecom.accounting.services.sales;

import ir.hitelecom.accounting.dto.SalesListDTO;
import ir.hitelecom.accounting.entities.sales.Sales;
import ir.hitelecom.accounting.entities.sales.Status;
import ir.hitelecom.accounting.repositories.UserRepository;
import ir.hitelecom.accounting.repositories.sales.SalesRepository;
import ir.hitelecom.accounting.repositories.stock.ProductSizeRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public List<Sales> fetchAll(SalesListDTO dto) {
        if (dto.getId() == null)
            return salesRepository.search(dto.getCustomer(), dto.getUser(), dto.getProductSize(), dto.getStatus(), getPageable(dto.getPageableDTO()));
        else
            return salesRepository.findAllById(dto.getId());
    }

    public Sales saveOrUpdate(Sales sales) {
        sales.setProductSize(productSizeRepository.findByCode(sales.getProductCode()));
        sales.setUser(userRepository.findByUsername(getLoggedInUsername()));
        if(sales.getStatus().equals(Status.IN_CART))
            sales.setInCartDate(LocalDateTime.now(ZoneId.systemDefault()));
        else
            sales.setFinishedDate(LocalDateTime.now(ZoneId.systemDefault()));
        return salesRepository.save(sales);
    }

    public void delete(Sales sales) {
        salesRepository.delete(sales);
    }

}
