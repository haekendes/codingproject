
package de.christ.picardcodingproject.data.entity;

import de.christ.picardcodingproject.enums.OfferStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "offers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_offer_number",
                        columnNames = "offer_number"
                ),
                @UniqueConstraint(
                        name = "uk_offer_inquiry",
                        columnNames = "inquiry_id"
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "offer_number", nullable = false)
    private String offerNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter
    private OfferStatus status = OfferStatus.DRAFT;

    @Column(name = "total_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "sent_at")
    @Setter
    private Instant sentAt;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inquiry_id", nullable = false, unique = true)
    private Inquiry inquiry;

    @OneToMany(
            mappedBy = "offer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<OfferPosition> positions = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public Offer(
            String offerNumber,
            Inquiry inquiry,
            Instant createdAt
    ) {
        this.offerNumber = offerNumber;
        this.inquiry = inquiry;
        this.createdAt = createdAt;

        inquiry.assignOffer(this);
    }

    public void addPosition(OfferPosition position) {
        positions.add(position);
        position.setOffer(this);
    }

    public void calculateTotalAmount() {
        this.totalAmount = positions.stream()
                .map(position ->
                        position.getQuantity().multiply(position.getUnitPrice())
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
