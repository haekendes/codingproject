
package de.christ.picardcodingproject.service;

import de.christ.picardcodingproject.data.entity.Offer;
import de.christ.picardcodingproject.data.repository.OfferRepository;
import de.christ.picardcodingproject.dto.offer.OfferPositionResponse;
import de.christ.picardcodingproject.dto.offer.OfferResponse;
import de.christ.picardcodingproject.enums.OfferStatus;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferSendingService {

    private static final Logger log =
            LoggerFactory.getLogger(OfferSendingService.class);

    private final OfferRepository offerRepository;
    private final OfferWebClient offerWebClient;

    @Transactional
    public int sendDraftOffers() {

        List<Offer> draftOffers =
                offerRepository.findAllByStatus(OfferStatus.DRAFT);

        int sentOffers = 0;

        for (Offer offer : draftOffers) {

            if (!offer.getStatus().canTransitionTo(OfferStatus.SENT)) {
                log.warn(
                        "Offer {} cannot be sent from status {}",
                        offer.getOfferNumber(),
                        offer.getStatus()
                );
                continue;
            }

            if (offer.getPositions().isEmpty()
                    || offer.getTotalAmount().signum() <= 0) {

                log.warn(
                        "Offer {} cannot be sent because it has no positions or total amount is not positive",
                        offer.getOfferNumber()
                );
                continue;
            }

            try {
                OfferResponse offerResponse = mapToResponse(offer);

                // Übermittlung an das Backend der Weboberfläche
                offerWebClient.sendOffer(offerResponse);

                // Status erst nach erfolgreicher Übermittlung ändern
                offer.setStatus(OfferStatus.SENT);
                offer.setSentAt(Instant.now());

                sentOffers++;

                log.info(
                        "Offer {} successfully sent to web frontend",
                        offer.getOfferNumber()
                );

            } catch (Exception exception) {
                log.error(
                        "Failed to send offer {} to web frontend",
                        offer.getOfferNumber(),
                        exception
                );
            }
        }

        return sentOffers;
    }

    private OfferResponse mapToResponse(Offer offer) {

        List<OfferPositionResponse> positions =
                offer.getPositions().stream()
                        .map(position -> new OfferPositionResponse(
                                position.getArticleName(),
                                position.getQuantity(),
                                position.getUnitPrice()
                        ))
                        .toList();

        return new OfferResponse(
                offer.getId(),
                offer.getOfferNumber(),
                offer.getInquiry().getId(),
                offer.getInquiry().getCustomerReference(),
                offer.getStatus(),
                offer.getTotalAmount(),
                offer.getCreatedAt(),
                offer.getSentAt(),
                positions
        );
    }
}
