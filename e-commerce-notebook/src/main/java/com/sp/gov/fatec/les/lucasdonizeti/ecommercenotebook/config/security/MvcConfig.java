package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * author LucasDonizeti
 */

@Configuration
@EnableWebSecurity
public class MvcConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    public MvcConfig(UserDetailsServiceImp userDetailsServiceImp) {
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable().authorizeRequests()
                .antMatchers(AccessControl.getCAMINHOS_PUBLICOS()).permitAll()
                //.antMatchers(HttpMethod.GET, AccessControl.getCAMINHOS_PUBLICOS_GET()).permitAll()
                //.antMatchers(HttpMethod.POST, AccessControl.getCAMINHOS_PUBLICOS_POST()).permitAll()

                .antMatchers("/adm/**").hasAuthority("ROLE_ADM")

                .anyRequest().authenticated()

                .and().formLogin()
                .loginPage("/usuario/login").permitAll()
                .loginProcessingUrl("/usuario/login")
                .usernameParameter("email").passwordParameter("senha")
                .failureUrl("/usuario/login-erro")
                .defaultSuccessUrl("/produto", true)
                .and().logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/produto")
        .and().exceptionHandling().accessDeniedPage("/usuario/acesso-negado");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImp).passwordEncoder(new BCryptPasswordEncoder());
    }

}
