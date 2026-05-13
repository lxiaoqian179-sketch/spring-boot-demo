package com.example.demo.auth;

import com.example.demo.common.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(
            @RequestBody LoginRequest request) {

        // 查 DB 找使用者
        Optional<User> userOpt = userService.findByUsername(request.getUsername());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401)
                    .body(ApiResponse.error("帳號不存在"));
        }

        User user = userOpt.get();

        // 比對密碼（目前明文比對，Week 4 會改成 BCrypt）
        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401)
                    .body(ApiResponse.error("密碼錯誤"));
        }

        // 產生 JWT
        String token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(
                ApiResponse.success(Map.of("token", token))
        );
    }
}