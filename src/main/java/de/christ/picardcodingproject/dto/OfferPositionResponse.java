package de.christ.picardcodingproject.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OfferPositionResponse(

        UUID id,

        String articleName,

        BigDecimal quantity,

        BigDecimal unitPrice

) {
}
