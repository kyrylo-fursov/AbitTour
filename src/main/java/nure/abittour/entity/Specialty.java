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
public class Specialty extends BaseEntity {

    private String competitionIdentifier; // Ідентифікатор конкурсу
    private String degreeOfHigherEducation; // Ступінь вищої освіти
    private String basisOfAdmission; // Основа вступу
    private String formOfEducation; // Форма здобуття освіти
    private String code; // код
    private String name; // Назва

}
