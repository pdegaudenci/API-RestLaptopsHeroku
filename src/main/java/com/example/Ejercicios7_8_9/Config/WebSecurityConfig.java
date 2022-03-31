package com.example.Ejercicios7_8_9.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/saludar").permitAll()
                .antMatchers("/api/laptops").hasRole("USER")
                .antMatchers("/api/laptops/{id}").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/api/laptops").hasRole("ADMIN")
           // Todo requerimiento debe estar autenticado
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }





    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder()) // Encriptar password
                .withUser("usuario1")
                .password(passwordEncoder().encode("1234"))
                .roles("USER").and()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER","ADMIN");
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




}
