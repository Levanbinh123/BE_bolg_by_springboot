package com.example.bolg_BE.dao;

import com.example.bolg_BE.entity.Category;
import com.example.bolg_BE.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "nguoi-dung")
public interface NguoiDungRepository extends JpaRepository<User, Integer> {
boolean existsByEmail(String email);
boolean existsByTenDangNhap(String tenDangNhap);
User findByTenDangNhap(String tenDangNhap);
User findByEmail(String email);
}
