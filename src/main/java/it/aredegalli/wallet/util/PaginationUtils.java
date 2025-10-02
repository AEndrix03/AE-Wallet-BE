package it.aredegalli.wallet.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtils {

    public static Pageable of(Integer page, Integer size, String sort, String direction) {
        Pageable pageable;

        if (sort != null && !sort.isEmpty()) {
            Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction)
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;
            pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
        } else {
            pageable = PageRequest.of(page, size);
        }

        return pageable;
    }

}
