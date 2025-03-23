package ru.kupidji.iknt.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/css/**", "/js/**", "/icons/**", "/images/**", "/favicon.ico").permitAll()
                    .requestMatchers("/error").permitAll()
                    .requestMatchers("/", "/about-us", "/news", "/teachers").permitAll()
                    .anyRequest().authenticated()
            }
            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
            .formLogin { formLogin: FormLoginConfigurer<HttpSecurity?> ->
                formLogin
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/admin")
                    .permitAll()
            }
            .logout { logout: LogoutConfigurer<HttpSecurity> ->
                logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .permitAll()
            }
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(passwordEncoder: PasswordEncoder): UserDetailsService {
        val user = User.builder()
            .username("admin")
            .password("password")
            .passwordEncoder { rawPassword: String? -> passwordEncoder.encode(rawPassword) }
            .build()

        return InMemoryUserDetailsManager(user)
    }
}
