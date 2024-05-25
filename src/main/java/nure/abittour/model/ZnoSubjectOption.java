package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import nure.abittour.model.enums.Subject;

import java.util.Objects;

@ToString(exclude = "competitiveOffer")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class ZnoSubjectOption extends BaseEntity {

    @Column
    Double coefficient;

    @Enumerated(EnumType.STRING)
    @Column
    Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competitive_offer_id")
    private CompetitiveOffer competitiveOffer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZnoSubjectOption that = (ZnoSubjectOption) o;
        return Objects.equals(coefficient, that.coefficient) && subject == that.subject;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coefficient, subject);
    }
}
