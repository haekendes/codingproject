package de.christ.picardcodingproject.service;

import de.christ.picardcodingproject.data.entity.Inquiry;
import de.christ.picardcodingproject.data.entity.Offer;
import de.christ.picardcodingproject.data.entity.OfferPosition;
import de.christ.picardcodingproject.data.repository.InquiryRepository;
import de.christ.picardcodingproject.data.repository.OfferRepository;
import de.christ.picardcodingproject.enums.InquiryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OfferGenerationService {

    private final InquiryRepository inquiryRepository;
    private final OfferRepository offerRepository;

    @Transactional
    public int generateOffersForOpenInquiries() {

        List<Inquiry> inquiries =
                inquiryRepository.findAllByStatus(InquiryStatus.OPEN);

        int generatedOffers = 0;

        for (Inquiry inquiry : inquiries) {

            if (offerRepository.existsByInquiryId(inquiry.getId())) {
                continue;
            }

            String offerNumber =
                    "OFF-" + UUID.randomUUID()
                            .toString()
                            .substring(0, 8)
                            .toUpperCase();

            Offer offer = new Offer(offerNumber, inquiry);

            inquiry.getPositions().forEach(position ->
                    offer.addPosition(
                            new OfferPosition(
                                    position.getArticleName(),
                                    position.getQuantity(),
                                    position.getUnitPrice()
                            )
                    )
            );

            offer.calculateTotalAmount();

            inquiry.setStatus(InquiryStatus.PROCESSED);

            offerRepository.save(offer);

            generatedOffers++;
        }

        return generatedOffers;
    }
}