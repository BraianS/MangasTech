package com.mangastech.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Braian
 *
 */
public enum Statu {
	@Schema(description = "Status do tipo COMPLETO,LANCADO e PAUSADO")
	COMPLETO,
	LANCANDO,
	PAUSADO	
}
