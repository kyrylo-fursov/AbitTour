package nure.abittour.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nure.abittour.model.enums.Subject;

@Entity
@Getter
@Setter
public class SubjectCoef extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speciality_id", nullable = false)
    private Speciality speciality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private Double coefficient;
}
