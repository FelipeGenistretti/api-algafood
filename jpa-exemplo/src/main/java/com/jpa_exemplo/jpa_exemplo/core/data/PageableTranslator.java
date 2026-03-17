package com.jpa_exemplo.jpa_exemplo.core.data;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

public class PageableTranslator {

    public static Pageable translate(Pageable pageable, Map<String, String> fieldsMapping) {

        var orders = pageable.getSort().stream()
            .map(order -> {
                String mappedField = fieldsMapping.getOrDefault(
                    order.getProperty(), 
                    order.getProperty()
                );

                return new Sort.Order(order.getDirection(), mappedField);
            })
            .collect(Collectors.toList());

        return PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            Sort.by(orders)
        );
    }
}
