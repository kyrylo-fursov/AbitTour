package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import nure.abittour.model.enums.Subject;

import java.math.BigDecimal;

@Entity
public class ChosenSubject extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    Application application;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Subject subject;


    @Column(nullable = false)
    Integer score;

    @Column(nullable = false)
    BigDecimal coefficient;
}
