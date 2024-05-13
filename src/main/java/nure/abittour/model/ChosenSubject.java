package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ChosenSubject extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    Subject subject;

    @Column(nullable = false)
    Integer score;

    @Column(nullable = false)
    Double coefficient;
}
