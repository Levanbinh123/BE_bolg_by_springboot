package com.example.bolg_BE.service;

import com.example.bolg_BE.dao.NguoiDungRepository;
import com.example.bolg_BE.entity.ThongBao;
import com.example.bolg_BE.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class TaiKhoanService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    private EmailService emailService;
    public ResponseEntity<?> dangKyNguoiDung(User user) {
//        if(nguoiDungRepository.existsByTenDangNhap(user.getTenDangNhap())) {
//            return ResponseEntity.badRequest().body(new ThongBao("ten da ton tai"));
//        }
        if(nguoiDungRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new ThongBao("email da ton tai!!"));
        }
        //ma hoa mat khau
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //gan va gui thong tin kich hoat
        user.setMaKichHoat(taoMaKichHoat());
        user.setDaKichHoat(false);
        //luu vao db
        User user_daDangKy=nguoiDungRepository.save(user);
        //gui emailcho nguoi dung kich hoat
        guiEmailKichHoat(user.getEmail(),user.getMaKichHoat());
        return ResponseEntity.ok("dang ky thanh cong");
    }
    //tao ma kich hoat ngau nhien
    private String taoMaKichHoat(){
        return UUID.randomUUID().toString();
    }
    private void guiEmailKichHoat(String email, String maKichHoat){
        String subject = "Kích hoạt tài khoản của bạn tại WebBanSach";
        String text = "Vui lòng sử dụng mã sau để kich hoạt cho tài khoản <"+email+">:<html><body><br/><h1>"+maKichHoat+"</h1></body></html>";
        text+="<br/> Click vào đường link để kích hoạt tài khoản: ";
        String url = "http://localhost:3000/kich-hoat/"+email+"/"+maKichHoat;
        text+=("<br/> <a href="+url+">"+url+"</a> ");

        emailService.sendMessage("tunletest1.email@gmail.com", email, subject, text);
    }
    public ResponseEntity<?> kichhoatTaiKhoan(String email, String maKichHoat) {
        User user = nguoiDungRepository.findByEmail(email);
        if(user == null) {
            return ResponseEntity.badRequest().body(new ThongBao("nguoi dung khong ton tai"));

        }
        if(user.getMaKichHoat().equals(maKichHoat)) {
            return ResponseEntity.badRequest().body(new ThongBao("Tài khoản đã được kích hoạt!"));
        }
        if(maKichHoat.equals(user.getMaKichHoat())) {
            user.setDaKichHoat(true);
            nguoiDungRepository.save(user);
            return ResponseEntity.ok("da kich hoat thanh cong");
        }else{
            return ResponseEntity.badRequest().body(new ThongBao("ma kich hoat khong chinh xac"));
        }
    }

}
