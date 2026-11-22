
package de.christ.picardcodingproject.dto.offer;

import de.christ.picardcodingproject.enums.OfferStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OfferResponse(

        UUID id,

        String offerNumber,

        UUID inquiryId,

        String customerReference,

        OfferStatus status,

        BigDecimal totalAmount,

        Instant createdAt,

        Instant sentAt,

        List<OfferPositionResponse> positions

) {
}
