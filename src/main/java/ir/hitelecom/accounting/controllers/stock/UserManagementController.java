package ir.hitelecom.accounting.controllers.stock;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shop/users")
public class UserManagementController {

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    @ResponseBody
    public List<User> fetchAll() {
        return userService.fetchShopUsers();
    }

    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public void create(@RequestBody User user) {
        userService.createShopUser(user);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody User user) {
        userService.delete(user);
    }

}
