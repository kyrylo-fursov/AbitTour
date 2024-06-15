package nure.abittour.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import nure.abittour.dto.UniversitySearchRequest;
import nure.abittour.model.University;
import org.springframework.data.jpa.domain.Specification;

public class UniversitySpecification implements Specification<University> {

    private final UniversitySearchRequest searchRequest;

    public UniversitySpecification(UniversitySearchRequest searchRequest) {
        this.searchRequest = searchRequest;
    }

    @Override
    public Predicate toPredicate(Root<University> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchRequest.getQuery() == null || searchRequest.getQuery().isEmpty()) {
            return criteriaBuilder.conjunction();
        }

        String searchPattern = "%" + searchRequest.getQuery().toLowerCase() + "%";
        Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchPattern);
        Predicate codePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("code")), searchPattern);

        return criteriaBuilder.or(namePredicate, codePredicate);
    }
}