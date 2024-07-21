package com.example.springsecurity.config;

import com.example.springsecurity.services.UserDetailService;
import com.example.springsecurity.utils.AuthenticationSuccesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private UserDetailService userDetailService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/home", "/register").permitAll();
                    registry.requestMatchers("/admin/**").hasAnyRole("ADMIN");
                    registry.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN");
                    registry.anyRequest().authenticated();

                })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/login")
                            .successHandler(new AuthenticationSuccesHandler())
                            .permitAll();
                })
//                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)  // to display login interface for secured endpoint
                .build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user = User.builder()
//                .username("user")
//                .password("$2a$12$eRN1VWjXt7/9vaLe1ZBjpub5KozA8DKT5/uIJxMv4J4ruWytDW8p2") // 1234
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("$2a$12$8/vbiffRnlc9BIkPM2tpee5ohFL82KTfeHFUFFTbOgseR1YF4IqkS") //5678
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }
    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailService;
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
