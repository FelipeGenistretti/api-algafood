package com.jpa_exemplo.jpa_exemplo.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
public class Problema {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private List<Field> fields;


    @Getter
    @Builder
    public static class Field {
        private String name;
        private String userMessage;
    }

}
