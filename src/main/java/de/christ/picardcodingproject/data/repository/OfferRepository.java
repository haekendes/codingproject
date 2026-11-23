
package de.christ.picardcodingproject.data.repository;

import de.christ.picardcodingproject.data.entity.Offer;
import de.christ.picardcodingproject.enums.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {

    List<Offer> findAllByStatus(OfferStatus status);

    List<Offer> findAllByInquiryCustomerReference(String customerReference);

    List<Offer> findAllByStatusAndInquiryCustomerReference(
            OfferStatus status,
            String customerReference
    );

    boolean existsByInquiryId(UUID inquiryId);

}
