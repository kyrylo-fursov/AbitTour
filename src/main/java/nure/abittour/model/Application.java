package nure.abittour.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Application extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    CompetitiveOffer competitiveOffer;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ChosenSubject> chosenSubjects;

    @Column(nullable = false)
    Double totalScore;

    @Column(nullable = false)
    Integer priority;
}