package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import java.util.Set;

@Entity
public class ZnoSubjectOption extends BaseEntity {
    @Column(nullable = false)
    Double coefficient;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @Column
    Integer kNumber;
}
