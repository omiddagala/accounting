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

import java.time.LocalDate;
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
            return salesRepository.search(dto.getCustomer(), dto.getUser(), dto.getProductSize(), dto.getStatus(), dto.getAddDate(), dto.getPaidDate(),dto.getFactorNumber(), getPageable(dto.getPageableDTO()));
        else
            return salesRepository.findAllById(dto.getId());
    }

    public Sales saveOrUpdate(Sales sales) {
        if(sales.getId() == null){
            sales.setProductSize(productSizeRepository.findByCode(sales.getProductCode()));
            sales.setPrice(sales.getProductSize().getProduct().getPrice());
            sales.setUser(userRepository.findByUsername(getLoggedInUsername()));
            sales.setAddDateTime(LocalDateTime.now(ZoneId.systemDefault()));
            sales.setAddDate(LocalDate.now(ZoneId.systemDefault()));
            return salesRepository.save(sales);
        }
        return salesRepository.save(sales);
    }

    public void finalizeFactor(List<Long> ids){
        Long factorNumber = ids.get(0);
        for (Long id:ids) {
            Sales sales = salesRepository.findById(id).get();
            sales.setStatus(Status.PAID);
            sales.setFactorNumber(factorNumber);
            sales.setPaidDateTime(LocalDateTime.now(ZoneId.systemDefault()));
            sales.setPaidDate(LocalDate.now(ZoneId.systemDefault()));
        }
    }

    public void delete(Sales sales) {
        salesRepository.delete(sales);
    }

}
