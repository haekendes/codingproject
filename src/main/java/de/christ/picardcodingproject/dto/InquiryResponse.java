
package de.christ.picardcodingproject.dto.inquiry;

import de.christ.picardcodingproject.enums.InquiryStatus;
import de.christ.picardcodingproject.dto.offer.OfferSummaryResponse;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record InquiryResponse(

        UUID id,

        String externalInquiryNumber,

        String customerReference,

        InquiryStatus status,

        Instant receivedAt,

        List<InquiryPositionResponse> positions,

        OfferSummaryResponse offer

) {

    public record InquiryPositionResponse(

            UUID id,

            String articleName,

            java.math.BigDecimal quantity,

            java.math.BigDecimal unitPrice

    ) {
    }
}
