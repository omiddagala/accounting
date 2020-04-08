package ir.hitelecom.accounting.controllers.sales;

import ir.hitelecom.accounting.entities.sales.Customer;
import ir.hitelecom.accounting.entities.stock.Size;
import ir.hitelecom.accounting.services.sales.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/shop/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/list")
    @ResponseBody
    public Iterable<Customer> fetchAll() {
        return customerService.fetchAll();
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

    @PostMapping("/findOne")
    @ResponseBody
    public Customer findOne(@RequestBody Customer customer) {
        return customerService.findOne(customer);
    }
}
