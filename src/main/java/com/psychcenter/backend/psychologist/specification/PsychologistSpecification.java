package com.psychcenter.backend.psychologist.specification;

import com.psychcenter.backend.psychologist.entity.Psychologist;
import org.springframework.data.jpa.domain.Specification;

public class PsychologistSpecification {

    public static Specification<Psychologist> hasSpecialization(String specialization) {
        return (root, query, cb) ->
                specialization == null ? null :
                        cb.like(cb.lower(root.get("specialization")),
                                "%" + specialization.toLowerCase() + "%");
    }

    public static Specification<Psychologist> hasLanguage(String language) {
        return (root, query, cb) ->
                language == null ? null :
                        cb.isMember(language, root.get("languages"));
    }

    public static Specification<Psychologist> hasMinExperience(Integer years) {
        return (root, query, cb) ->
                years == null ? null :
                        cb.greaterThanOrEqualTo(root.get("experienceYears"), years);
    }
}