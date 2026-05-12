package com.psychcenter.backend.repository;

import com.psychcenter.backend.model.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogPost, Long> {

    Page<BlogPost> findAll(Pageable pageable);

    boolean existsByTitle(String title);

}