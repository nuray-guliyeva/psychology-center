package com.psychcenter.backend.psychologist.specification;

import com.psychcenter.backend.psychologist.entity.Psychologist;
import org.springframework.data.jpa.domain.Specification;

public class PsychologistSpecification {

    public static Specification<Psychologist> hasSpecialization(String specialization) {
        return (root, query, cb) -> {
            if (specialization == null) return cb.conjunction();
            return cb.like(cb.lower(root.get("specialization")),
                    "%" + specialization.toLowerCase() + "%");
        };
    }

    public static Specification<Psychologist> hasLanguage(String language) {
        return (root, query, cb) -> {
            if (language == null) return cb.conjunction();
            return cb.isMember(language, root.get("languages"));
        };
    }

    public static Specification<Psychologist> hasMinExperience(Integer years) {
        return (root, query, cb) -> {
            if (years == null) return cb.conjunction();
            return cb.greaterThanOrEqualTo(root.get("experienceYears"), years);
        };
    }
}