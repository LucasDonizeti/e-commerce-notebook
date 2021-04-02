package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.security;

import lombok.Getter;

/**
 * author LucasDonizeti
 */
public class AccessControl {
    @Getter
    private static final String[] CAMINHOS_PUBLICOS = {
            "/js/**",
            "/produto",
            "/produto/detalhes/**",
            "/usuario/login",
            "/cliente/carrinho/**",
            "/cliente/cadastro"
    };
}
