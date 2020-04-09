package ir.hitelecom.accounting.services.sales;

import ir.hitelecom.accounting.dto.CustomerListDTO;
import ir.hitelecom.accounting.entities.sales.Customer;
import ir.hitelecom.accounting.repositories.sales.CustomerRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService extends BaseService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> fetchAll(CustomerListDTO dto) {
        if (dto.getId() == null)
            return customerRepository.search(dto.getName(), dto.getFamily(), dto.getNationalCode(), dto.getMobile(), getPageable(dto.getPageableDTO()));
        else
            return customerRepository.findAllById(dto.getId());
    }

    public Customer saveOrUpdate(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }
}
