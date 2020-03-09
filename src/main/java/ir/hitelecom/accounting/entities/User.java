package ir.hitelecom.accounting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.hitelecom.accounting.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "auth_users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    private String password;
    private String plain;
    private String role;
    private String name;
    private String family;
    private String mobile;
    @ManyToOne
    @JsonIgnore
    private User parent;
    // recoveryCode / recoveryCodeCounter / tokenCode / tokenCodeCounter
    @JsonIgnore
    private String recoveryField;
    @JsonIgnore
    private boolean enabled = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlain() {
        return plain;
    }

    public void setPlain(String plain) {
        this.plain = plain;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRecoveryField() {
        return recoveryField;
    }

    public void setRecoveryField(String recoveryField) {
        this.recoveryField = recoveryField;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(this.role);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }

    public List<String> convertServerRolesToClient() {
        List<String> clients = new ArrayList<>();
        this.getAuthorities().forEach(role -> {
            clients.add(Role.findClientRole(role.getAuthority()));
        });
        return clients;
    }

    public UserDTO convertEntityToDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(this.getName());
        userDTO.setFamily(this.getFamily());
        userDTO.setUsername(this.getUsername());
        userDTO.setRoles(convertServerRolesToClient());
        return userDTO;
    }

    public void populateEntityByDTO(UserDTO dto) {
        this.setName(dto.getName());
        this.setFamily(dto.getFamily());
        this.setMobile(dto.getMobile());
    }
}
