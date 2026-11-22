
package de.christ.picardcodingproject.dto.inquiry;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record InquiryCreateRequest(

        @NotBlank
        @Size(max = 100)
        String externalInquiryNumber,

        @NotBlank
        @Size(max = 100)
        String customerReference,

        @NotEmpty
        @Valid
        List<InquiryPositionResponse> positions

) {
}
