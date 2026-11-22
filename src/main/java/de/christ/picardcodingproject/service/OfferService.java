
package de.christ.picardcodingproject.service;

import de.christ.picardcodingproject.data.entity.Inquiry;
import de.christ.picardcodingproject.data.entity.Offer;
import de.christ.picardcodingproject.data.entity.OfferPosition;
import de.christ.picardcodingproject.data.repository.InquiryRepository;
import de.christ.picardcodingproject.data.repository.OfferRepository;
import de.christ.picardcodingproject.dto.offer.OfferPositionResponse;
import de.christ.picardcodingproject.dto.offer.OfferResponse;
import de.christ.picardcodingproject.enums.InquiryStatus;
import de.christ.picardcodingproject.enums.OfferStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OfferService {

    private final InquiryRepository inquiryRepository;
    private final OfferRepository offerRepository;

    public OfferService(
            InquiryRepository inquiryRepository,
            OfferRepository offerRepository
    ) {
        this.inquiryRepository = inquiryRepository;
        this.offerRepository = offerRepository;
    }

    public OfferResponse createOffer(UUID inquiryId) {

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Inquiry not found."
                ));

        if (offerRepository.existsByInquiryId(inquiryId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "An offer already exists for this inquiry."
            );
        }

        String offerNumber = generateOfferNumber();

        Offer offer = new Offer(offerNumber, inquiry);

        inquiry.getPositions().forEach(inquiryPosition -> {
            OfferPosition offerPosition = new OfferPosition(
                    inquiryPosition.getArticleName(),
                    inquiryPosition.getQuantity(),
                    inquiryPosition.getUnitPrice()
            );

            offer.addPosition(offerPosition);
        });

        offer.calculateTotalAmount();

        inquiry.setStatus(InquiryStatus.PROCESSED);

        Offer savedOffer = offerRepository.save(offer);

        return mapToResponse(savedOffer);
    }

    public OfferResponse sendOffer(UUID offerId) {

        Offer offer = findOffer(offerId);

        if (offer.getStatus() != OfferStatus.DRAFT) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Only draft offers can be sent."
            );
        }

        if (offer.getPositions().isEmpty()
                || offer.getTotalAmount().signum() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "An offer must contain at least one position and have a total amount greater than zero."
            );
        }

        offer.setStatus(OfferStatus.SENT);
        offer.setSentAt(Instant.now());

        return mapToResponse(offer);
    }

    public OfferResponse acceptOffer(UUID offerId) {

        Offer offer = findOffer(offerId);

        if (offer.getStatus() != OfferStatus.SENT) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Only sent offers can be accepted."
            );
        }

        offer.setStatus(OfferStatus.ACCEPTED);

        return mapToResponse(offer);
    }

    public OfferResponse rejectOffer(UUID offerId) {

        Offer offer = findOffer(offerId);

        if (offer.getStatus() != OfferStatus.SENT) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Only sent offers can be rejected."
            );
        }

        offer.setStatus(OfferStatus.REJECTED);

        return mapToResponse(offer);
    }

    @Transactional(readOnly = true)
    public OfferResponse getOffer(UUID offerId) {

        Offer offer = findOffer(offerId);

        return mapToResponse(offer);
    }

    private Offer findOffer(UUID offerId) {
        return offerRepository.findById(offerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Offer not found."
                ));
    }

    private String generateOfferNumber() {
        return "OFF-" + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
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
