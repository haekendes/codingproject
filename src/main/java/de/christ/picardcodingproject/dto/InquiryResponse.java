
package de.christ.picardcodingproject.dto;

import de.christ.picardcodingproject.enums.InquiryStatus;

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
}
