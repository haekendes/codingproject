
package de.christ.picardcodingproject.controller;

import de.christ.picardcodingproject.dto.inquiry.InquiryCreateRequest;
import de.christ.picardcodingproject.dto.inquiry.InquiryResponse;
import de.christ.picardcodingproject.service.InquiryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/inquiries")
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

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

    @GetMapping("/external/{externalNumber}")
    public InquiryResponse getInquiryByExternalNumber(
            @PathVariable String externalNumber
    ) {
        return inquiryService.getInquiryByExternalNumber(externalNumber);
    }
}
