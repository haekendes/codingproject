package de.christ.picardcodingproject.enums;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum OfferStatus {
    DRAFT,
    SENT,
    ACCEPTED,
    REJECTED;

    private static final Map<OfferStatus, Set<OfferStatus>> ALLOWED_TRANSITIONS =
            Map.of(
                    DRAFT, EnumSet.of(SENT),
                    SENT, EnumSet.of(ACCEPTED, REJECTED),
                    ACCEPTED, EnumSet.noneOf(OfferStatus.class),
                    REJECTED, EnumSet.noneOf(OfferStatus.class)
            );

    public boolean canTransitionTo(OfferStatus target) {
        return ALLOWED_TRANSITIONS.get(this).contains(target);
    }
}
