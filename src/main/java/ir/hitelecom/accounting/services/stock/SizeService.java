package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.stock.Size;
import ir.hitelecom.accounting.repositories.stock.SizeRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SizeService extends BaseService {

    @Autowired
    private SizeRepository sizeRepository;

    public List<Size> fetchAll(Size size) {
        return sizeRepository.search(size.getValue());
    }

    public Size saveOrUpdate(Size size) {
        if (sizeRepository.existsByValue(size.getValue())) {
            throw new RuntimeException(getErrorMessage("sizeExists"));
        }
        return sizeRepository.save(size);
    }

    public void delete(Size size) {
        sizeRepository.delete(size);
    }
}
