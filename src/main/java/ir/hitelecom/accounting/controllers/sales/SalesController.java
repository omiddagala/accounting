package ir.hitelecom.accounting.controllers.sales;

import ir.hitelecom.accounting.dto.PageableDTO;
import ir.hitelecom.accounting.entities.sales.Sales;
import ir.hitelecom.accounting.services.sales.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shop/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @PostMapping("/list")
    @ResponseBody
    public List<Sales> fetchAll(@RequestBody PageableDTO dto) {
        return salesService.fetchAll(dto);
    }

    @PostMapping("/save")
    @ResponseBody
    public Sales saveOrUpdate(@RequestBody Sales sales) {
        return salesService.saveOrUpdate(sales);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody Sales sales) {
        salesService.delete(sales);
    }


}
