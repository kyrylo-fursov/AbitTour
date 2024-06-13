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
    Speciality speciality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    Subject subject;

    @Column(nullable = false)
    Double coefficient;
}
