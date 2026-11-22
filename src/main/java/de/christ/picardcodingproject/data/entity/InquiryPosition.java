
package de.christ.picardcodingproject.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "inquiry_positions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InquiryPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "article_name", nullable = false)
    private String articleName;

    @Column(nullable = false, precision = 19, scale = 3)
    private BigDecimal quantity;

    @Column(name = "unit_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inquiry_id", nullable = false)
    private Inquiry inquiry;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public InquiryPosition(
            String articleName,
            BigDecimal quantity,
            BigDecimal unitPrice
    ) {
        this.articleName = articleName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    void setInquiry(Inquiry inquiry) {
        this.inquiry = inquiry;
    }
}
