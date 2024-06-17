package nure.abittour.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nure.abittour.model.enums.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Speciality extends BaseEntity {
    @Column(nullable = false, unique = true)
    String code;

    @Column(nullable = false)
    String name;

    @Column
    String specialization;

    @Column(nullable = false)
    Boolean isInDemand;

    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SubjectCoef> subjectCoefs = new ArrayList<>();
}
