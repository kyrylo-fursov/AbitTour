package nure.abittour.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import nure.abittour.model.BaseEntity;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class University extends BaseEntity {

    private String name;

    @ManyToOne
    private Region region;

}
