package com.psychcenter.backend.specification;

import com.psychcenter.backend.model.entity.Psychologist;
import org.springframework.data.jpa.domain.Specification;

public class PsychologistSpecification {

    public static Specification<Psychologist> hasSpecialization(String specialization) {
        return (root, query, cb) ->
                specialization == null
                        ? cb.conjunction()
                        : cb.like(cb.lower(root.get("specialization")),
                        "%" + specialization.toLowerCase() + "%");
    }

    public static Specification<Psychologist> hasLanguage(String language) {
        return (root, query, cb) ->
                language == null ? cb.conjunction()
                        : cb.isMember(language, root.get("languages"));
    }
}