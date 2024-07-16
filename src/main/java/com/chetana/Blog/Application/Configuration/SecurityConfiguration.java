package com.chetana.Blog.Application.Configuration;

import com.chetana.Blog.Application.Security.CustomeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
//@EnableWebSecurity
class SecurityConfig {

    private CustomeUserDetailsService customeUserDetailsService;

    @Bean
   public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception { //The defaultSecurityFilterChain method configures HttpSecurity
       http
               .csrf(csrf -> csrf.disable())
               .authorizeHttpRequests(authz -> authz
                       .anyRequest().authenticated()
               )
               .httpBasic(Customizer.withDefaults());

       return http.build();
   }

 /*   @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/


   /* protected void configure(AuthenticationManagerBuilder auth) throws Exception{ //Configures authentication settings.
        auth.userDetailsService(customeUserDetailsService).passwordEncoder(passwordEncoder()); //load the user details and encode the password
    }*/

    protected AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customeUserDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
