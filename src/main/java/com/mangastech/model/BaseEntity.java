package com.mangastech.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@MappedSuperclass
/**
 * Base entity com a propriedade ID para ser estendido
 * 
 * @author Braian
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}