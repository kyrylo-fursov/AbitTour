package nure.abittour.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Speciality extends BaseEntity {
    @Column(nullable = false)
    String code;

    @Column(nullable = false)
    String name;

    @Column
    String specialization;
}
