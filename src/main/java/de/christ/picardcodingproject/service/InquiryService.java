
package de.christ.picardcodingproject.service;

import de.christ.picardcodingproject.data.entity.Inquiry;
import de.christ.picardcodingproject.data.entity.InquiryPosition;
import de.christ.picardcodingproject.data.repository.InquiryRepository;
import de.christ.picardcodingproject.dto.inquiry.InquiryCreateRequest;
import de.christ.picardcodingproject.dto.inquiry.InquiryPositionDto;
import de.christ.picardcodingproject.dto.inquiry.InquiryResponse;
import de.christ.picardcodingproject.enums.InquiryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    public InquiryResponse createInquiry(InquiryCreateRequest request) {

        if (inquiryRepository.existsByExternalInquiryNumber(
                request.externalInquiryNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "An inquiry with this external inquiry number already exists."
            );
        }

        Inquiry inquiry = new Inquiry(
                request.externalInquiryNumber(),
                request.customerReference(),
                Instant.now()
        );

        request.positions().forEach(position -> {
            InquiryPosition inquiryPosition = new InquiryPosition(
                    position.articleName(),
                    position.quantity(),
                    position.unitPrice()
            );

            inquiry.addPosition(inquiryPosition);
        });

        Inquiry savedInquiry = inquiryRepository.save(inquiry);

        return mapToResponse(savedInquiry);
    }

    @Transactional(readOnly = true)
    public InquiryResponse getInquiry(UUID id) {

        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Inquiry not found."
                ));

        return mapToResponse(inquiry);
    }

    @Transactional(readOnly = true)
    public List<InquiryResponse> searchInquiries(
            InquiryStatus status,
            String customerReference
    ) {
        List<Inquiry> inquiries;

        if (status != null && customerReference != null) {
            inquiries = inquiryRepository
                    .findAllByStatusAndCustomerReference(status, customerReference);
        } else if (status != null) {
            inquiries = inquiryRepository.findAllByStatus(status);
        } else if (customerReference != null) {
            inquiries = inquiryRepository.findAllByCustomerReference(customerReference);
        } else {
            inquiries = inquiryRepository.findAll();
        }

        return inquiries.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private InquiryResponse mapToResponse(Inquiry inquiry) {

        List<InquiryPositionDto> positions =
                inquiry.getPositions().stream()
                        .map(position -> new InquiryPositionDto(
                                position.getArticleName(),
                                position.getQuantity(),
                                position.getUnitPrice()
                        ))
                        .toList();

        return new InquiryResponse(
                inquiry.getId(),
                inquiry.getExternalInquiryNumber(),
                inquiry.getCustomerReference(),
                inquiry.getStatus(),
                inquiry.getReceivedAt(),
                positions
        );
    }
}
