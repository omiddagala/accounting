package ir.hitelecom.accounting.controllers;

import ir.hitelecom.accounting.dto.*;
import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/anonymous/register")
    @ResponseBody
    public void register(@RequestBody User user) {
        userService.register(user);
    }

    @PostMapping("/anonymous/forgetPass")
    @ResponseBody
    public void forgetPass(@RequestBody String mobile) {
        userService.forgerPass(mobile);
    }

    @PostMapping("/anonymous/recoveryCode")
    @ResponseBody
    public RecoveryResponseDTO recoveryCode(@RequestBody RecoveryDTO dto) throws Exception {
        RecoveryResponseDTO recovery = userService.recovery(dto);
        if (recovery.getException() != null)
            throw recovery.getException();
        return recovery;
    }

    @PostMapping("/anonymous/recoveryChangePass")
    @ResponseBody
    public void recoveryChangePass(@RequestBody RecoveryChangePassDTO dto) throws Exception {
        Exception exception = userService.recoveryChangePass(dto);
        if (exception != null)
            throw exception;
    }

    @PostMapping("/authenticated/update")
    @ResponseBody
    public UserDTO updateProfile(@RequestBody UserDTO dto) {
        userService.update(dto);
        return dto;
    }

    @PostMapping("/authenticated/changePass")
    @ResponseBody
    public void recoveryChangePass(@RequestBody ChangePassDTO dto) {
        userService.changePass(dto);
    }
}
