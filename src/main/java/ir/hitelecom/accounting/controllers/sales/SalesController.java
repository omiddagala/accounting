package ir.hitelecom.accounting.controllers.sales;

import ir.hitelecom.accounting.entities.sales.Customer;
import ir.hitelecom.accounting.entities.sales.Sales;
import ir.hitelecom.accounting.services.sales.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/shop/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @PostMapping("/list")
    @ResponseBody
    public Iterable<Sales> fetchAll() {
        return salesService.fetchAll();
    }

    @PostMapping("/save")
    @ResponseBody
    public Sales saveOrUpdate(@RequestBody Sales customer) {
        return salesService.saveOrUpdate(customer);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody Sales sales) {
        salesService.delete(sales);
    }

    @PostMapping("/findByCustomer")
    @ResponseBody
    public Iterable<Sales> findByCustomer(@RequestBody Customer customer) {
        return salesService.fetchAllByCustomer(customer);
    }
}
