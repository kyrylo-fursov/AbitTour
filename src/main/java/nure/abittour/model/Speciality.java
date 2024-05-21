package nure.abittour.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Speciality extends BaseEntity {
    @Column(nullable = false)
    String code;

    @Column(nullable = false)
    String name;

    @Column
    String specialization;

}
