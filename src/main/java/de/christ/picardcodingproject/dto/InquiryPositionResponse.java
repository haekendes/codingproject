package de.christ.picardcodingproject.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record InquiryPositionResponse(

        @NotBlank
        @Size(max = 255)
        String articleName,

        @NotNull
        @DecimalMin(value = "0.001")
        BigDecimal quantity,

        @NotNull
        @DecimalMin(value = "0.00")
        BigDecimal unitPrice

) {
}
