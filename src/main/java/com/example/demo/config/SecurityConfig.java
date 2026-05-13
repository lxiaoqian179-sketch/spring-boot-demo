package com.example.demo.config;


import com.example.demo.auth.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //告訴 Spring Boot 這是一個設定檔
@EnableWebSecurity //啟用 Spring Security 的網頁安全功能
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean  //把這個方法的回傳值交給 Spring Boot 管理
    //SecurityFilterChain → 定義「請求進來時要經過哪些過濾規則」
    //HttpSecurity http → 用來設定安全規則的物件
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())//關閉 CSRF 防護
                // (CSRF 是一種攻擊方式，但 API 模式用 JWT 驗證，不需要這個防護)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //        設定為 無狀態（STATELESS）
                //        意思是：伺服器不記住使用者的登入狀態
                //        每次請求都要自己帶 JWT token 來證明身份



                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll() // 登入不需要 token
                        .requestMatchers("/", "/index.html", "/**.css", "/**.js").permitAll() // 加這行
                        .anyRequest().authenticated()              // 其他都需要 token
                )
                .addFilterBefore(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class); // 加入 JwtFilter

        return http.build();
        //把以上所有設定組合起來，回傳一個完整的安全規則物件
    }
}
//總結 : 設定伺服器的安全規則：關閉 CSRF、使用 JWT 無狀態驗證、設定API 部分公開與不公開。