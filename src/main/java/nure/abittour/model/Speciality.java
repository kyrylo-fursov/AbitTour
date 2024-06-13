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
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String specialization;

    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubjectCoef> subjectCoefs = new ArrayList<>();
}
