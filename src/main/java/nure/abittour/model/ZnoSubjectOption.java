package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import nure.abittour.model.enums.Subject;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class ZnoSubjectOption extends BaseEntity {
    @Column(nullable = true)
    Double coefficient;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    Subject subject;

    @ManyToOne
    @JoinColumn(name = "competitive_offer_id")
    private CompetitiveOffer competitiveOffer;
}
