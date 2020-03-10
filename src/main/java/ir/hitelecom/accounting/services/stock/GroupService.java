package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.stock.Group;
import ir.hitelecom.accounting.repositories.stock.GroupRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupService extends BaseService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> fetchAll(){
        return groupRepository.findByUsername(getLoggedInUsername());
    }

    public Group saveOrUpdate(Group group) {
        return groupRepository.save(group);
    }

    public void delete(Group group) {
        groupRepository.delete(group);
    }
}
