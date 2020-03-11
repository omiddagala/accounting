package ir.hitelecom.accounting.controllers.stock;

import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import ir.hitelecom.accounting.entities.stock.Timeline;
import ir.hitelecom.accounting.services.stock.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shop/timeline")
public class TimelineController {

    @Autowired
    private TimelineService timelineService;

    @PostMapping("/list")
    @ResponseBody
    public List<Timeline> fetchAll(@RequestBody ProductSize productSize) {
        return timelineService.list(productSize);
    }

    @PostMapping("/save")
    @ResponseBody
    public void save(@RequestBody Timeline timeline) {
        timelineService.save(timeline);
    }
}
