package com.example.bolg_BE.controller;
import com.example.bolg_BE.entity.User;
import com.example.bolg_BE.entity.dtos.KichHoatRequest;
import com.example.bolg_BE.security.JwtResponse;
import com.example.bolg_BE.security.LoginRequest;
import com.example.bolg_BE.service.JwtService;
import com.example.bolg_BE.service.TaiKhoanService;
import com.example.bolg_BE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tai-khoan")
public class TaiKhoanController
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @CrossOrigin(origins = "*")
    @PostMapping("/dang-ky")
    public ResponseEntity<?> dangKyNguoiDung(@Validated @RequestBody User user){
        ResponseEntity<?> response = taiKhoanService.dangKyNguoiDung(user);
        return response;
    }
    @PostMapping("/kich-hoat")
    public ResponseEntity<?> kichHoat(@RequestBody KichHoatRequest request) {
        return taiKhoanService.kichhoatTaiKhoan(request.getEmail(), request.getMaKichHoat());
    }
    @PostMapping("/dang-nhap")
    public ResponseEntity<?> dangNhap(@RequestBody LoginRequest loginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            // Nếu xác thực thành công, tạo token JWT
            if(authentication.isAuthenticated()){
                final String jwt = jwtService.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwt));

        } }catch(AuthenticationException e){
                // Xác thực không thành công, trả về lỗi hoặc thông báo
                return ResponseEntity.badRequest().body("Tên đăng nhập hặc mật khẩu không chính xác.");
    }
            return ResponseEntity.badRequest().body("Xác thực không thành công.");}
}