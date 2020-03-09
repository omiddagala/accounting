package ir.hitelecom.accounting.services;

import ir.hitelecom.accounting.dto.*;
import ir.hitelecom.accounting.entities.Role;
import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;

@Service
@Transactional
public class UserService extends BaseService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();
    private static final int recoveryCodeUsageLimit = 2;
    private static final int recoveryTokenUsageLimit = 1;
    private static final int recoveryCodeLength = 6;
    private static final int recoveryTokenLength = 8;

    public void register(User user) {
        user.setPlain(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.SHXXOP_ADXXMIN.getServer());
        try {
            User parent = userRepository.saveAndFlush(user);
            user.setParent(parent);
        } catch (Exception e) {
            if (e.getCause().getCause().toString().contains("Detail: Key (mobile)=("))
                throw new RuntimeException(getErrorMessage("foundMobile"));
            else if (e.getCause().getCause().toString().contains("Detail: Key (username)=("))
                throw new RuntimeException(getErrorMessage("foundUsername"));
            throw e;
        }
    }

    public void update(UserDTO dto) {
        User user = userRepository.findByUsername(getLoggedInUsername());
        user.populateEntityByDTO(dto);
        try {
            userRepository.saveAndFlush(user);
        } catch (Exception e) {
            if (e.getCause().getCause().toString().contains("Detail: Key (mobile)=("))
                throw new RuntimeException(getErrorMessage("foundMobile"));
            else if (e.getCause().getCause().toString().contains("Detail: Key (username)=("))
                throw new RuntimeException(getErrorMessage("foundUsername"));
            throw e;
        }
        //        when client saves new user's information in localstorage, needs these fields
        dto.setUsername(user.getUsername());
        dto.setRoles(user.convertServerRolesToClient());
    }

    public void changePass(ChangePassDTO dto) {
        User user = userRepository.findByUsername(getLoggedInUsername());
        if (passwordEncoder.matches(dto.getOldPass(), user.getPassword())) {
            user.setPlain(dto.getNewPass());
            user.setPassword(passwordEncoder.encode(dto.getNewPass()));
        } else
            throw new RuntimeException(getErrorMessage("wrongPass"));
    }

    public void forgerPass(String mobile) {
        User user = userRepository.findByMobile(mobile);
        if (user == null)
            throw new RuntimeException(getErrorMessage("userNotFound"));
        String recoveryCode = randomString(recoveryCodeLength);
        user.setRecoveryField(recoveryCode + "/" + recoveryCodeUsageLimit);
        //#TODO its for testing
//        smsSenderService.sendTemplateSms(mobile, recoveryCode, null, null, "confirmCode", new ArrayList<>());
        System.out.println("------- recovery Code : " + recoveryCode);
    }

    // we return exception in this service so that in case of wrong verification code counter persists
    public RecoveryResponseDTO recovery(RecoveryDTO dto) {
        User user = userRepository.findByMobile(dto.getMobile());
        userRecoveryPassValidation(user);
        String[] rs = user.getRecoveryField().split("/");
        String recoveryCode = rs[0];
        int recoveryCodeCounter = Integer.valueOf(rs[1]);
        RecoveryResponseDTO returnDto = new RecoveryResponseDTO();
        if (recoveryCodeCounter <= 0)
            throw new RuntimeException(getErrorMessage("verificationCodeUsage"));
        if (recoveryCode.equals(dto.getRecoveryCode())) {
            String token = randomString(recoveryTokenLength);
            user.setRecoveryField(recoveryCode + "/" + 0 + "/" + token + "/" + recoveryTokenUsageLimit);
            returnDto.setUsername(user.getUsername());
            returnDto.setToken(token);
            return returnDto;
        } else {
            user.setRecoveryField(recoveryCode + "/" + (recoveryCodeCounter - 1));
        }
        returnDto.setException(new RuntimeException(getErrorMessage("wrongVerificationCode")));
        return returnDto;
    }

    // we return exception in this service so that in case of wrong verification code counter persists
    public Exception recoveryChangePass(RecoveryChangePassDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername());
        userRecoveryPassValidation(user);
        String[] rs = user.getRecoveryField().split("/");
        String recoveryCode = rs[0];
        int recoveryCodeCounter = Integer.valueOf(rs[1]);
        String token = rs[2];
        int tokenCounter = Integer.valueOf(rs[3]);
        if (tokenCounter <= 0)
            throw new RuntimeException(getErrorMessage("verificationCodeUsage"));
        if (token.equals(dto.getToken())) {
            user.setPlain(dto.getNewPass());
            user.setPassword(passwordEncoder.encode(dto.getNewPass()));
            user.setRecoveryField(recoveryCode + "/" + recoveryCodeCounter + "/" + token + "/" + 0);
            return null;
        } else {
            user.setRecoveryField(recoveryCode + "/" + recoveryCodeCounter + "/" + token + "/" + (tokenCounter - 1));
            return new RuntimeException(getErrorMessage("wrongVerificationCode"));
        }
    }

    private void userRecoveryPassValidation(User user) {
        if (user == null)
            throw new RuntimeException(getErrorMessage("userNotFound"));
        if (user.getRecoveryField() == null)
            throw new RuntimeException(getErrorMessage("wrongVerificationCode"));
    }

    public String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public List<User> fetchShopUsers() {
        User user = userRepository.findByUsername(getLoggedInUsername());
        return userRepository.findByParent(user.getParent());
    }

    public void createShopUser(User user) {
        user.setPlain(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.findServerRole(user.getRole()));
        try {
            userRepository.saveAndFlush(user);
            User parent = userRepository.findByUsername(getLoggedInUsername());
            user.setParent(parent);
        } catch (Exception e) {
            if (e.getCause().getCause().toString().contains("Detail: Key (mobile)=("))
                throw new RuntimeException(getErrorMessage("foundMobile"));
            else if (e.getCause().getCause().toString().contains("Detail: Key (username)=("))
                throw new RuntimeException(getErrorMessage("foundUsername"));
            throw e;
        }
    }
}
