package ir.hitelecom.accounting.services.sales;

import ir.hitelecom.accounting.entities.sales.Customer;
import ir.hitelecom.accounting.entities.sales.Sales;
import ir.hitelecom.accounting.repositories.sales.SalesRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SalesService extends BaseService {
    @Autowired
    private SalesRepository salesRepository;

    public Iterable<Sales> fetchAll() {
        return salesRepository.findAll();
    }

    public Sales saveOrUpdate(Sales sales) {
        return salesRepository.save(sales);
    }

    public void delete(Sales sales) {
        salesRepository.delete(sales);
    }

    public Iterable<Sales> fetchAllByCustomer(Customer customer){
        return salesRepository.findByCustomer(customer);
    }
}
