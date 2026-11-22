
package de.christ.picardcodingproject.data.entity;

import de.christ.picardcodingproject.enums.InquiryStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "inquiries",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_inquiry_external_number",
                        columnNames = "external_inquiry_number"
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "external_inquiry_number", nullable = false)
    private String externalInquiryNumber;

    @Column(name = "customer_reference", nullable = false)
    private String customerReference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter
    private InquiryStatus status = InquiryStatus.OPEN;

    @Column(name = "received_at", nullable = false)
    private Instant receivedAt;

    @OneToMany(
            mappedBy = "inquiry",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<InquiryPosition> positions = new ArrayList<>();

    @OneToOne(mappedBy = "inquiry", fetch = FetchType.LAZY)
    private Offer offer;

    public Inquiry(
            String externalInquiryNumber,
            String customerReference,
            Instant receivedAt
    ) {
        this.externalInquiryNumber = externalInquiryNumber;
        this.customerReference = customerReference;
        this.receivedAt = receivedAt;
    }

    public void addPosition(InquiryPosition position) {
        positions.add(position);
        position.setInquiry(this);
    }

    public void removePosition(InquiryPosition position) {
        positions.remove(position);
        position.setInquiry(null);
    }
}
