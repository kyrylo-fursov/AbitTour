package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Subject extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
}