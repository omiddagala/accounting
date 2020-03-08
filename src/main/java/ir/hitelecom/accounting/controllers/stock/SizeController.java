package ir.hitelecom.accounting.controllers.stock;

import ir.hitelecom.accounting.entities.stock.Size;
import ir.hitelecom.accounting.services.stock.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shop/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @PostMapping("/list")
    @ResponseBody
    public List<Size> fetchAll(@RequestBody Size size) {
        return sizeService.fetchAll(size);
    }

    @PostMapping("/save")
    @ResponseBody
    public Size saveOrUpdate(@RequestBody Size size) {
        return sizeService.saveOrUpdate(size);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody Size size) {
        sizeService.delete(size);
    }
}
