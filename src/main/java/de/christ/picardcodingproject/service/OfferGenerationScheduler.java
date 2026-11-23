package de.christ.picardcodingproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OfferGenerationScheduler {

    private static final Logger log =
            LoggerFactory.getLogger(OfferGenerationScheduler.class);

    private final OfferGenerationService offerGenerationService;

    public OfferGenerationScheduler(
            OfferGenerationService offerGenerationService
    ) {
        this.offerGenerationService = offerGenerationService;
    }

    @Scheduled(
            fixedDelayString = "${app.offer-generation.fixed-delay:3000}",
            initialDelayString = "${app.offer-generation.initial-delay:1000}"
    )
    public void generateOffers() {

        log.info("Starting scheduled offer generation");

        int generatedOffers =
                offerGenerationService.generateOffersForOpenInquiries();

        log.info(
                "Scheduled offer generation finished. Generated offers: {}",
                generatedOffers
        );
    }
}