package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.stock.Size;
import ir.hitelecom.accounting.repositories.stock.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    public List<Size> fetchAll(Size size) {
        return sizeRepository.search(size.getValue());
    }

    public Size saveOrUpdate(Size size) {
        return sizeRepository.save(size);
    }

    public void delete(Size size) {
        sizeRepository.delete(size);
    }
}
