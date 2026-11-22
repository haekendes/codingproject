
package de.christ.picardcodingproject.dto.offer;

import de.christ.picardcodingproject.enums.OfferStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OfferSummaryResponse(

        UUID id,

        String offerNumber,

        OfferStatus status,

        BigDecimal totalAmount,

        Instant createdAt,

        Instant sentAt

) {
}
