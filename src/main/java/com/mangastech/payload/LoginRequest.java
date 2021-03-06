package com.mangastech.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Braian
 *
 */
@Schema
public class LoginRequest {

	@Schema(description = "Username do Usuário",example = "admin",required = true)
    @NotBlank
    @Size(min = 3, max = 60)
    private String username;

	@Schema(description = "Password do Usuário",example = "@admin",required = true)
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}