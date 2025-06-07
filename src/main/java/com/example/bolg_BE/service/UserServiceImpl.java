package com.example.bolg_BE.service;

import com.example.bolg_BE.dao.NguoiDungRepository;
import com.example.bolg_BE.dao.QuyenRepository;
import com.example.bolg_BE.entity.Quyen;
import com.example.bolg_BE.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private NguoiDungRepository nguoiDungRepository;
    private QuyenRepository quyenRepository;
    @Autowired
    public UserServiceImpl(NguoiDungRepository nguoiDungRepository, QuyenRepository quyenRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
        this.quyenRepository = quyenRepository;

    }

    @Override
    public User findByUserName(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user1 = findByUserName(username);
        if (user1 == null) {
            throw new UsernameNotFoundException("tai khoan khong ton tai");
    }
      return new org.springframework.security.core.userdetails.User(user1.getTenDangNhap(), user1.getPassword(), rolesToAuthorities(user1.getQuyens()));
}
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Quyen> quyens) {
        return quyens.stream().map(quyen -> new SimpleGrantedAuthority(quyen.getTenQuyen())).collect(Collectors.toList());
    }


}
