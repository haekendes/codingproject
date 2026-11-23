
package de.christ.picardcodingproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OfferProcessingScheduler {

    private static final Logger log =
            LoggerFactory.getLogger(OfferProcessingScheduler.class);

    private final OfferGenerationService offerGenerationService;
    private final OfferSendingService offerSendingService;

    public OfferProcessingScheduler(
            OfferGenerationService offerGenerationService,
            OfferSendingService offerSendingService
    ) {
        this.offerGenerationService = offerGenerationService;
        this.offerSendingService = offerSendingService;
    }

    @Scheduled(
            fixedDelayString = "${app.offer-processing.fixed-delay:60000}",
            initialDelayString = "${app.offer-processing.initial-delay:10000}"
    )
    public void processOffers() {

        log.info("Starting scheduled offer processing");

        try {
            int generatedOffers =
                    offerGenerationService.generateOffersForOpenInquiries();

            log.info("Generated offers: {}", generatedOffers);

            int sentOffers =
                    offerSendingService.sendDraftOffers();

            log.info("Successfully sent offers: {}", sentOffers);

        } catch (Exception exception) {
            log.error("Scheduled offer processing failed", exception);
        }

        log.info("Scheduled offer processing finished");
    }
}
