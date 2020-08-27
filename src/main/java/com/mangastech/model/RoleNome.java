package com.mangastech.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Braian
 *
 */
public enum RoleNome{
    @Schema(description = "Cargos do tipo ROLE_USER e ROLE_ADMIN")
    ROLE_USER,ROLE_ADMIN
}