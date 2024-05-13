package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import nure.abittour.model.enums.Subject;

@Entity
public class ZnoSubjectOption extends BaseEntity {
    @Column(nullable = false)
    Double coefficient;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Subject subject;

    @ManyToOne
    @JoinColumn(name = "competitive_offer_id")
    private CompetitiveOffer competitiveOffer;
}
