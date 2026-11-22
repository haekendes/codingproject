
package de.christ.picardcodingproject.data.repository;

import de.christ.picardcodingproject.data.entity.InquiryPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InquiryPositionRepository extends JpaRepository<InquiryPosition, UUID> {

    List<InquiryPosition> findAllByInquiryId(UUID inquiryId);
}
