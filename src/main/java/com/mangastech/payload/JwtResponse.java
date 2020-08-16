package com.mangastech.payload;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import io.swagger.v3.oas.annotations.media.Schema;

public class JwtResponse {

	@Schema(description = "Token de acesso")
    private String accessToken;
	@Schema(description = "Tipo do Token")
    private String tokenType = "Bearer";
	@Schema(description = "Username do Usuário")
    private String username;
	@Schema(description = "Cargos do Usuário")
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String accessToken,String username, Collection<? extends GrantedAuthority> authorities) {
        this.accessToken = accessToken;
        this.username = username;
        this.authorities = authorities;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthority(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}