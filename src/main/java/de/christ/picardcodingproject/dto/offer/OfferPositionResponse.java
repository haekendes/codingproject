package de.christ.picardcodingproject.dto.offer;

import java.math.BigDecimal;
import java.util.UUID;

public record OfferPositionResponse(

        String articleName,

        BigDecimal quantity,

        BigDecimal unitPrice

) {
}
