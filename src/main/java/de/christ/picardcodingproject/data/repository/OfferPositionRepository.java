
package de.christ.picardcodingproject.data.repository;

import de.christ.picardcodingproject.data.entity.OfferPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OfferPositionRepository extends JpaRepository<OfferPosition, UUID> {

    List<OfferPosition> findAllByOfferId(UUID offerId);
}
