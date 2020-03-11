package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import ir.hitelecom.accounting.entities.stock.Timeline;
import ir.hitelecom.accounting.repositories.stock.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TimelineService {

    @Autowired
    private TimelineRepository timelineRepository;

    public void save(Timeline timeline) {
        timeline.setDate(getNowDate());
        timelineRepository.save(timeline);
    }

    public List<Timeline> list(ProductSize productSize) {
        return timelineRepository.findByProductSize(productSize);
    }

    private static Date getNowDate() {
        LocalDateTime now = LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("Asia/Tehran")).toLocalDateTime();
        return Date.from(now.atZone(ZoneId.of("UTC")).toInstant());
    }
}
