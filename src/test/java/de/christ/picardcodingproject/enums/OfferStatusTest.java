package de.christ.picardcodingproject.enums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OfferStatusTest {

    @ParameterizedTest
    @CsvSource({
            "DRAFT, SENT, true",
            "SENT, ACCEPTED, true",
            "SENT, REJECTED, true",
            "DRAFT, ACCEPTED, false",
            "DRAFT, REJECTED, false",
            "SENT, SENT, false",
            "ACCEPTED, SENT, false",
            "ACCEPTED, REJECTED, false",
            "REJECTED, SENT, false",
            "REJECTED, ACCEPTED, false"
    })
    void shouldValidateStatusTransitions(
            OfferStatus current,
            OfferStatus target,
            boolean expected
    ) {
        assertEquals(expected, current.canTransitionTo(target));
    }
}