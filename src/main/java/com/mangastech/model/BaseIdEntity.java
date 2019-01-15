package com.mangastech.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Base entity com a propriedade ID para ser estendido
 * 
 * @author Braian
 */
@MappedSuperclass
public abstract class BaseIdEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}