
package de.christ.picardcodingproject.service;

import de.christ.picardcodingproject.dto.offer.OfferResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OfferWebClient {

    private final RestClient restClient;
    private final String offerUrl;

    public OfferWebClient(
            @Value("${app.web-frontend.offer-url}") String offerUrl
    ) {
        this.restClient = RestClient.create();
        this.offerUrl = offerUrl;
    }

    public void sendOffer(OfferResponse offer) {

        restClient.post()
                .uri(offerUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(offer)
                .retrieve()
                .toBodilessEntity();
    }
}
