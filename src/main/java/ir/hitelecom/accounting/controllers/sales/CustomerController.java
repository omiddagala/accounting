package ir.hitelecom.accounting.controllers.sales;

import ir.hitelecom.accounting.dto.CustomerListDTO;
import ir.hitelecom.accounting.entities.sales.Customer;
import ir.hitelecom.accounting.services.sales.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shop/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/list")
    @ResponseBody
    public List<Customer> fetchAll(@RequestBody CustomerListDTO dto) {
        return customerService.fetchAll(dto);
    }

    @PostMapping("/save")
    @ResponseBody
    public Customer saveOrUpdate(@RequestBody Customer customer) {
        return customerService.saveOrUpdate(customer);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody Customer customer) {
        customerService.delete(customer);
    }
}
