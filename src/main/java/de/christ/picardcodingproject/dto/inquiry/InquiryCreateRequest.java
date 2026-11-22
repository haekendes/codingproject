
package de.christ.picardcodingproject.dto.inquiry;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record InquiryCreateRequest(

        @NotBlank(message = "External inquiry number is required.")
        @Size(max = 100, message = "External inquiry number must not exceed 100 characters.")
        String externalInquiryNumber,

        @NotBlank(message = "Customer reference is required.")
        @Size(max = 100, message = "Customer reference must not exceed 100 characters.")
        String customerReference,

        @NotEmpty(message = "At least one inquiry position is required.")
        @Valid
        List<InquiryPositionDto> positions

) {
}
