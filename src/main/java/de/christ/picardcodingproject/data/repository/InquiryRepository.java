
package de.christ.picardcodingproject.data.repository;

import de.christ.picardcodingproject.data.entity.Inquiry;
import de.christ.picardcodingproject.enums.InquiryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InquiryRepository extends JpaRepository<Inquiry, UUID> {

    List<Inquiry> findAllByStatus(InquiryStatus status);

    boolean existsByExternalInquiryNumber(String externalInquiryNumber);

    List<Inquiry> findAllByCustomerReference(String customerReference);

    List<Inquiry> findAllByStatusAndCustomerReference(
            InquiryStatus status,
            String customerReference
    );
}
