package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class ZnoSubjectOption extends BaseEntity {
    @Column(nullable = false)
    Double coefficient;

    @ManyToOne
    private CompetitiveOffer competitiveOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    Subject subject;
}
