package com.mangastech.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/*@Entity
@Table(name="mangas_autor")*/
public class MangasAutor {
	/*
	@EmbeddedId
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="manga_idmanga",columnDefinition = "idmanga")
	@MapsId("mangaIdmanga")
	private MangasEntity manga;
	
	@ManyToOne
	@JoinColumn(name="autorIdautor",columnDefinition = "idautor")
	@MapsId("autorIdautor")
	private AutorEntity autor;*/
}
