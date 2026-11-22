
package de.christ.picardcodingproject.dto.inquiry;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record InquiryPositionDto(

        @NotBlank(message = "Article name is required.")
        @Size(max = 255, message = "Article name must not exceed 255 characters.")
        String articleName,

        @NotNull(message = "Quantity is required.")
        @DecimalMin(value = "0.001", message = "Quantity must be greater than zero.")
        BigDecimal quantity,

        @NotNull(message = "Unit price is required.")
        @DecimalMin(value = "0.00", message = "Unit price must not be negative.")
        BigDecimal unitPrice

) {
}
