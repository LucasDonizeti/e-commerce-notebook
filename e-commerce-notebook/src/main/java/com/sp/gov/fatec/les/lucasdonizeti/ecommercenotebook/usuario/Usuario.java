package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_usuario")
@SQLDelete(sql = "update _usuario set habilitado = 0 where id = ?")
public class Usuario extends EntidadeDominio implements Serializable, UserDetails {

    @Column(name = "login", nullable = false, length = 50)
    private String login;

    @Column(name = "senha", nullable = false, length = 60)
    private String senha;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "tipoUsuario", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> roles=new ArrayList<>();
        if (tipoUsuario == TipoUsuario.ADMINISTRADOR){
            roles.add(asRole("ROLE_ADM"));
        }else{
            roles.add(asRole("ROLE_CLI"));
        }
        return roles;
    }
    private GrantedAuthority asRole(String role){
        GrantedAuthority grantedAuthority=new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        };
        return grantedAuthority;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.habilitado;
    }
}
