package ir.hitelecom.accounting.services.sales;

import ir.hitelecom.accounting.dto.PageableDTO;
import ir.hitelecom.accounting.entities.sales.Customer;
import ir.hitelecom.accounting.repositories.sales.CustomerRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService extends BaseService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> fetchAll(PageableDTO dto) {
        return customerRepository.findAll(getPageable(dto)).getContent();
    }

    public Customer saveOrUpdate(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public Customer findOne(Customer customer) {
        Optional<Customer> optional = customerRepository.findByNationalCode(customer.getNationalCode());
        return optional.orElse(null);
    }
}
