package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Subject extends BaseEntity {
    @Column(nullable = false, unique = true)
    String name;
}