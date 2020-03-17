package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Group;
import ir.hitelecom.accounting.repositories.UserRepository;
import ir.hitelecom.accounting.repositories.stock.GroupRepository;
import ir.hitelecom.accounting.services.BaseService;
import ir.hitelecom.accounting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupService extends BaseService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Group> fetchAll(){
        User user = userRepository.findByUsername(getLoggedInUsername());
        return groupRepository.findByUsername(user.getParent().getUsername());
    }

    public Group saveOrUpdate(Group group) {
        User user = userRepository.findByUsername(getLoggedInUsername());
        group.setUsername(user.getParent().getUsername());
        return groupRepository.save(group);
    }

    public void delete(Group group) {
        groupRepository.delete(group);
    }
}
