package ir.hitelecom.accounting.controllers.stock;

import ir.hitelecom.accounting.entities.stock.Reservoir;
import ir.hitelecom.accounting.entities.stock.Size;
import ir.hitelecom.accounting.services.stock.ReservoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shop/reservoir")
public class ReservoirController {

    @Autowired
    private ReservoirService reservoirService;

    @PostMapping("/list")
    @ResponseBody
    public List<Reservoir> fetchAll(@RequestBody Reservoir reservoir) {
        return reservoirService.search(reservoir);
    }

    @PostMapping("/save")
    @ResponseBody
    public Reservoir saveOrUpdate(@RequestBody Reservoir reservoir) {
        return reservoirService.saveOrUpdate(reservoir);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody Reservoir reservoir) {
        reservoirService.delete(reservoir);
    }
}
