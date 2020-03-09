package ir.hitelecom.accounting.repositories;

import ir.hitelecom.accounting.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByMobile(String mobile);
    List<User> findByParent(User user);
}
