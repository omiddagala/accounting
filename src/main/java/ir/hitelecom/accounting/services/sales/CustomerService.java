package ir.hitelecom.accounting.services.sales;

import ir.hitelecom.accounting.dto.CustomerDTO;
import ir.hitelecom.accounting.dto.CustomerListDTO;
import ir.hitelecom.accounting.dto.DailyCustomerListDTO;
import ir.hitelecom.accounting.entities.sales.Customer;
import ir.hitelecom.accounting.repositories.sales.CustomerRepository;
import ir.hitelecom.accounting.repositories.sales.SalesRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService extends BaseService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SalesRepository salesRepository;

    public List<Customer> fetchAll(CustomerListDTO dto) {
        if (dto.getId() == null)
            return customerRepository.search(dto.getName(), dto.getFamily(), dto.getNationalCode(), dto.getMobile(), getPageable(dto.getPageableDTO()));
        else {
            List<Customer> byId = customerRepository.findAllById(dto.getId());
            if(byId.isEmpty())
                throw new NullPointerException("customerNotFound");
            return byId;
        }
    }

    public List<Customer> fetchAllUnpaid(DailyCustomerListDTO dto){
        List<Customer> result = salesRepository.findCustomerId(dto.getName(),dto.getFamily(),dto.getNationalCode(),dto.getMobile(),dto.getStatus(),dto.getAddDate(),dto.getPaidDate());
        Pageable pageable = getPageable(dto.getPageableDTO());
        int start =(int) pageable.getOffset();
        int end =(start + pageable.getPageSize()) > result.size() ? result.size() : (start + pageable.getPageSize());
        if(end < start)
            return new ArrayList<>();
        Page<Customer> pages = new PageImpl<>(result.subList(start, end), pageable, result.size());
        return pages.getContent();
    }

    public Customer saveOrUpdate(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public List<Long> fetchFactorNumber(CustomerDTO dto) {
        List<Long> result = salesRepository.findCustomerFactorNumbers(dto.getCustomer());
        Pageable pageable = getPageable(dto.getPageableDTO());
        int start =(int) pageable.getOffset();
        int end =(start + pageable.getPageSize()) > result.size() ? result.size() : (start + pageable.getPageSize());
        if(end < start)
            return new ArrayList<>();
        Page<Long> pages = new PageImpl<>(result.subList(start, end), pageable, result.size());
        return pages.getContent();
    }
}
