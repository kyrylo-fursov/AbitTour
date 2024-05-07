package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Student extends BaseEntity {
    @Column(nullable = false)
    String name;
}
