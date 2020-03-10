package ir.hitelecom.accounting.controllers.stock;

import ir.hitelecom.accounting.entities.stock.Group;
import ir.hitelecom.accounting.services.stock.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shop/size")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/list")
    @ResponseBody
    public List<Group> fetchAll() {
        return groupService.fetchAll();
    }

    @PostMapping("/save")
    @ResponseBody
    public Group saveOrUpdate(@RequestBody Group group) {
        return groupService.saveOrUpdate(group);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody Group group) {
        groupService.delete(group);
    }
}
