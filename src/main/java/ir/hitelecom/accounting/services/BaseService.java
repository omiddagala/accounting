package ir.hitelecom.accounting.services;

import ir.hitelecom.accounting.dto.PageableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;

@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
public class BaseService {
    @Autowired
    private Environment env;

    protected String getErrorMessage(String key) {
        return env.getProperty(key);
    }

    protected Pageable getPageable(PageableDTO dto) {
        Sort sort = Sort.by(Sort.Direction.ASC.toString().equals(dto.getDirection()) ? Sort.Direction.ASC : Sort.Direction.DESC, dto.getSortBy());
        return PageRequest.of(dto.getPage(), dto.getSize(), sort);
    }

    protected String getLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
