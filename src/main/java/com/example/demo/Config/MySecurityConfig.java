package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    /**BASIC AUthentication*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/public/**").permitAll()
//                /**Any url after public will be permited
//                 * and other requests need authentication*/
//                .antMatchers("/signin").permitAll()
                .antMatchers("/public/**").hasRole("NORMAL")
                .antMatchers("/note/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
//                .httpBasic();
          /** httpBasic used for basic authentication*/
                .formLogin(); /**This is used for form based authentication you can log in and logout*/
//            .loginPage("/signin");/*this is used to changed the defalut login path to signin
//                 It need to be public so that we can use it*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("chinu").password(passwordEncoder().encode("2556")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("jhon").password(passwordEncoder().encode("2556")).roles("NORMAL");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
///**Here we are use plain text as password so there fore we are writing NoOpPasswordEncoder
// * This is not used in protection level*/
//    }
    /**Form based authentication*/
}






















//package com.example.demo.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//
//@Configuration
////@EnableWebSecurity
//public class MySecurityConfig  {
//@Bean
//public PasswordEncoder passwordEncoder(){
//    return  new BCryptPasswordEncoder();
//}
//    @Bean
//    public UserDetailsService userDetailService(){
//        UserDetails  normalUser = User
//                                     .withUsername("chinu")
//                                     .password(passwordEncoder().encode("2556"))
//                                     .roles("NORMAL")
//                                     .build();
//        UserDetails adminUser = User
//                                      .withUsername("chinu1")
//                                      .password(passwordEncoder().encode("2556"))
//                                      .roles("ADMIN")
//                                      .build();
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(normalUser,adminUser);
//        return inMemoryUserDetailsManager;
//
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers(new AntPathRequestMatcher("/note/get"))
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin();
//return httpSecurity.build();
//    }
//}
