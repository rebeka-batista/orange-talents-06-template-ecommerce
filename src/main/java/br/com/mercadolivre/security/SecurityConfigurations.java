package br.com.mercadolivre.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuario/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/categoria/**").permitAll()
                .antMatchers(HttpMethod.POST, "/produto/**").permitAll()
                .antMatchers(HttpMethod.GET, "/produto/**").permitAll()
                .antMatchers(HttpMethod.POST, "/compra/**").permitAll()
                .antMatchers(HttpMethod.POST, "/retorno-pagseguro/**").permitAll()
                .antMatchers(HttpMethod.POST, "/retorno-paypal/**").permitAll()
                .antMatchers(HttpMethod.POST, "/notas-fiscais/**").permitAll()
                .antMatchers(HttpMethod.POST, "/ranking/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();
    }


    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs",
                "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

}
