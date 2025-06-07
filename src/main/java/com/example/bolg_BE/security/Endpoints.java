package com.example.bolg_BE.security;

public class Endpoints {
    public static final String front_end_host="http://localhost:3000";
    public static final String[] PUBLIC_GET_ENDPOINTS = {
            "/post",
            "/categories",
            "/nguoi-dung/search/existsByTenDangNhap",
            "/nguoi-dung/search/existsByEmail",
            "/tai-khoan/kich-hoat",

    };
    public static final String[] PUBLIC_POST_ENDPOINTS = {
            "/post",
            "/categories",
            "/tai-khoan/**"
    };
    public static final String[] PUBLIC_DELETE_ENDPOINTS = {
            "/post/**",
            "/categories/**",
    };
    public static final String[] ADMIN_GET_ENDPOINTS = {
            "/users",
            "/users/**",
    };
    public static final String[] ADMIN_POST_ENDPOINTS = {
            "/users",
            "/users/**",
    };

}
