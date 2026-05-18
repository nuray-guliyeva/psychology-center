package com.psychcenter.backend.specification;

// Imports Psychologist entity
import com.psychcenter.backend.model.entity.Psychologist;

// Spring Data JPA Specification interface
// Used for creating dynamic database queries
import org.springframework.data.jpa.domain.Specification;

// Utility class for building Psychologist query filters
public class PsychologistSpecification {

    // Specification for filtering psychologists by specialization
    public static Specification<Psychologist> hasSpecialization(
            String specialization
    ) {

        // Lambda expression that creates query condition
        return (root, query, cb) ->

                // If specialization is null,
                // return always-true condition
                specialization == null

                        // conjunction() means no filtering
                        ? cb.conjunction()

                        // Otherwise search specialization using LIKE query
                        : cb.like(

                        // Converts database value to lowercase
                        cb.lower(
                                root.get("specialization")
                        ),

                        // Search pattern
                        // Example:
                        // "%anxiety%"
                        "%" + specialization.toLowerCase() + "%"
                );
    }

    // Specification for filtering psychologists by language
    public static Specification<Psychologist> hasLanguage(
            String language
    ) {

        // Lambda expression that creates query condition
        return (root, query, cb) ->

                // If language is null,
                // return condition with no filtering
                language == null

                        ? cb.conjunction()

                        // Checks if language exists inside languages collection
                        : cb.isMember(
                        language,
                        root.get("languages")
                );
    }
}