package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.stock.Reservoir;
import ir.hitelecom.accounting.repositories.stock.ReservoirRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservoirService extends BaseService {

    @Autowired
    private ReservoirRepository reservoirRepository;

    public List<Reservoir> search(Reservoir reservoir) {
        return reservoirRepository.search(reservoir.getName());
    }

    public Reservoir saveOrUpdate(Reservoir reservoir) {
        return reservoirRepository.save(reservoir);
    }

    public void delete(Reservoir reservoir) {
        reservoirRepository.delete(reservoir);
    }
}
