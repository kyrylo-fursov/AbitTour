package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class University extends BaseEntity {
    @Column(nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    Region region;
}
