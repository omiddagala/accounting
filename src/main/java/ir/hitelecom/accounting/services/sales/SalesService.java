package ir.hitelecom.accounting.services.sales;

import ir.hitelecom.accounting.dto.sales.FinalizeFactorDTO;
import ir.hitelecom.accounting.dto.sales.ReportDTO;
import ir.hitelecom.accounting.dto.sales.ReportResultDTO;
import ir.hitelecom.accounting.dto.sales.SalesListDTO;
import ir.hitelecom.accounting.entities.sales.Sales;
import ir.hitelecom.accounting.entities.sales.Status;
import ir.hitelecom.accounting.entities.stock.ProductSize;
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
            return salesRepository.search(dto.getCustomer(), dto.getUser(), dto.getProductSize(), dto.getStatus(), dto.getAddDate(), dto.getPaidDate(), dto.getFactorNumber(), getPageable(dto.getPageableDTO()));
        else
            return salesRepository.findAllById(dto.getId());
    }

    public Sales saveOrUpdate(Sales sales) {
        if (sales.getId() == null) {
            ProductSize productSize = productSizeRepository.findByReservoirAndCode(userRepository.findByUsername(getLoggedInUsername()).getReservoir(),sales.getProductCode());
            // #TODO search with id (wrong print)
            if(productSize==null)
                productSize = productSizeRepository.findByReservoirAndId(userRepository.findByUsername(getLoggedInUsername()).getReservoir(),sales.getProductCode());
            if (productSize.getCount() < sales.getAmount())
                throw new RuntimeException("productCountOutOfRage");
            productSize.setCount(productSize.getCount() - sales.getAmount());
            sales.setProductSize(productSize);
            sales.setPrice(productSize.getProduct().getPrice());
            sales.setUser(userRepository.findByUsername(getLoggedInUsername()));
            sales.setAddDateTime(LocalDateTime.now(ZoneId.systemDefault()));
            sales.setAddDate(LocalDate.now(ZoneId.systemDefault()));
            sales.setStatus(Status.UNPAID);
            return salesRepository.save(sales);
        }
        Sales byId = salesRepository.findById(sales.getId()).get();
        int diff = byId.getAmount() - sales.getAmount();
        int newValue = byId.getProductSize().getCount() + diff;
        if (diff != 0) {
            if (newValue < 0)
                throw new RuntimeException("productCountOutOfRage");
            byId.getProductSize().setCount(newValue);
        }
        return salesRepository.save(sales);
    }

    public Long finalizeFactor(FinalizeFactorDTO dto) {
        Long factorNumber = dto.getIds().get(0);
        for (Long id : dto.getIds()) {
            Sales sales = salesRepository.findById(id).get();
            sales.setBankAccount(dto.getBankAccount());
            sales.setStatus(Status.PAID);
            sales.setFactorNumber(factorNumber);
            sales.setPaidDateTime(LocalDateTime.now(ZoneId.systemDefault()));
            sales.setPaidDate(LocalDate.now(ZoneId.systemDefault()));
        }
        return factorNumber;
    }

    public ReportResultDTO report(ReportDTO dto) {
        ReportResultDTO result = new ReportResultDTO();
        if (dto.getFrom() == null)
            dto.setFrom( LocalDate.of(1, 1, 1));
        result.setSales(salesRepository.report(dto.getCustomer(), dto.getUser(), dto.getProductSize(), Status.PAID, dto.getFrom(), dto.getTo(), dto.getBankAccount(), getPageable(dto.getPageableDTO())));
        result.setTotal(salesRepository.reportSum(dto.getCustomer(), dto.getUser(), dto.getProductSize(), Status.PAID, dto.getFrom(), dto.getTo(), dto.getBankAccount()));
        return result;
    }

    public void delete(Sales sales) {
        Sales byId = salesRepository.findById(sales.getId()).get();
        ProductSize productSize = productSizeRepository.findById(byId.getProductSize().getId()).get();
        productSize.setCount(productSize.getCount() + byId.getAmount());
        salesRepository.delete(sales);
    }
}
