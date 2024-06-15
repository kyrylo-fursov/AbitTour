package nure.abittour.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import nure.abittour.dto.CompetitiveOfferFilterDTO;
import nure.abittour.model.CompetitiveOffer;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CompetitiveOfferSpecification implements Specification<CompetitiveOffer> {

    private final CompetitiveOfferFilterDTO filter;

    public CompetitiveOfferSpecification(CompetitiveOfferFilterDTO filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<CompetitiveOffer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getProgramName() != null) {
            predicates.add(criteriaBuilder.like(root.get("programName"), "%" + filter.getProgramName() + "%"));
        }
        if (filter.getEnrolmentBase() != null) {
            predicates.add(criteriaBuilder.equal(root.get("enrolmentBase"), filter.getEnrolmentBase()));
        }
        if (filter.getEducationalLevel() != null) {
            predicates.add(criteriaBuilder.equal(root.get("educationalLevel"), filter.getEducationalLevel()));
        }
        if (filter.getSpecialityId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("speciality").get("id"), filter.getSpecialityId()));
        }
        if (filter.getUniversityId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("university").get("id"), filter.getUniversityId()));
        }
        if (filter.getTypeOfOffer() != null) {
            predicates.add(criteriaBuilder.equal(root.get("typeOfOffer"), filter.getTypeOfOffer()));
        }
        if (filter.getFormOfEducation() != null) {
            predicates.add(criteriaBuilder.equal(root.get("formOfEducation"), filter.getFormOfEducation()));
        }
        if (filter.getEnrolledCourse() != null) {
            predicates.add(criteriaBuilder.equal(root.get("enrolledCourse"), filter.getEnrolledCourse()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}