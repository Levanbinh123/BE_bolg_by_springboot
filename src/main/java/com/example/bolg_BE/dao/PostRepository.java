package com.example.bolg_BE.dao;

import com.example.bolg_BE.entity.Category;
import com.example.bolg_BE.entity.Post;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "post")
public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);
}
