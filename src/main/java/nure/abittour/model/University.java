package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class University extends BaseEntity {
    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String code;

    @Column
    String websiteUrl;

    @ManyToOne
    @JoinColumn(name = "region_id")
    Region region;
}
