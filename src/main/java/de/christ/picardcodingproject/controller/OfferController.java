
package de.christ.picardcodingproject.controller;

import de.christ.picardcodingproject.dto.offer.OfferResponse;
import de.christ.picardcodingproject.enums.OfferStatus;
import de.christ.picardcodingproject.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/{offerId}")
    public OfferResponse getOffer(@PathVariable UUID offerId) {
        return offerService.getOffer(offerId);
    }

    @PatchMapping("/{offerId}/accept")
    public OfferResponse acceptOffer(@PathVariable UUID offerId) {
        return offerService.acceptOffer(offerId);
    }

    @PatchMapping("/{offerId}/reject")
    public OfferResponse rejectOffer(@PathVariable UUID offerId) {
        return offerService.rejectOffer(offerId);
    }

    @GetMapping
    public List<OfferResponse> searchOffers(
            @RequestParam(required = false) OfferStatus status,
            @RequestParam(required = false) String customerReference
    ) {
        return offerService.searchOffers(status, customerReference);
    }
}
