package ir.hitelecom.accounting.controllers.sales;

import ir.hitelecom.accounting.dto.sales.CustomerListDTO;
import ir.hitelecom.accounting.dto.sales.DailyCustomerListDTO;
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

    @PostMapping("/unpaidList")
    @ResponseBody
    public List<Customer> fetchAllUnpaid(@RequestBody DailyCustomerListDTO dto) {
        return customerService.fetchAllUnpaid(dto);
    }

    @PostMapping("/factorNumber")
    @ResponseBody
    public List<Long> fetchFactorNumber(@RequestBody Customer customer) {
        return customerService.fetchFactorNumber(customer);
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
