package com.example.a.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.regex.Pattern;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Pattern bcryptPattern = Pattern.compile("^\\$2[aby]\\$.*");
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return bcrypt.encode(rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                if (encodedPassword == null) {
                    return false;
                }
                if (bcryptPattern.matcher(encodedPassword).matches()) {
                    return bcrypt.matches(rawPassword, encodedPassword);
                }
                return rawPassword != null && rawPassword.toString().equals(encodedPassword);
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                        // 允许所有人访问首页、关于、前台博文、前台类别及静态资源
                        .requestMatchers("/", "/about", "/posts/**", "/types/**", "/login", "/css/**", "/js/**").permitAll()
                        // 仅管理员访问后台
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/admin/posts", true) // 登录成功默认跳到博文管理
                        .permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}