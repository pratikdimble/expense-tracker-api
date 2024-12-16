package com.cd.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmsErrorResponse {
    private String errorMessage;
    private String errorCode;
    private LocalDateTime timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> fieldErrors;
}
