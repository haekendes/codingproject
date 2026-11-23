
package de.christ.picardcodingproject.controller;

import de.christ.picardcodingproject.dto.inquiry.InquiryCreateRequest;
import de.christ.picardcodingproject.dto.inquiry.InquiryResponse;
import de.christ.picardcodingproject.enums.InquiryStatus;
import de.christ.picardcodingproject.service.InquiryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inquiries")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InquiryResponse createInquiry(
            @Valid @RequestBody InquiryCreateRequest request
    ) {
        return inquiryService.createInquiry(request);
    }

    @GetMapping("/{inquiryId}")
    public InquiryResponse getInquiry(@PathVariable UUID inquiryId) {
        return inquiryService.getInquiry(inquiryId);
    }

    @GetMapping
    public List<InquiryResponse> searchInquiries(
            @RequestParam(required = false) InquiryStatus status,
            @RequestParam(required = false) String customerReference
    ) {
        return inquiryService.searchInquiries(status, customerReference);
    }
}
