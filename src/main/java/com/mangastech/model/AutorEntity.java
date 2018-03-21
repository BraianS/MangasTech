package com.mangastech.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
@Table(name = "Autor")
public class AutorEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	
	
	private String info;
	
	@JsonIgnoreProperties("autor")
	private  Set<MangasEntity> manga;
		
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	/*@OneToMany(mappedBy="autor",targetEntity= MangasEntity.class,fetch = FetchType.LAZY)*/
	@OneToMany(mappedBy="autor",targetEntity= MangasEntity.class, fetch = FetchType.LAZY, orphanRemoval = true)
	
	 @Cascade({CascadeType.ALL,CascadeType.DELETE})
	public Set<MangasEntity> getManga() {
		return manga;
	}
	
	public void setManga(Set<MangasEntity> manga) {
		this.manga = manga;
	}
	
	public AutorEntity() {
		this.manga = new HashSet<>();
	}

	@Column(columnDefinition = "TEXT")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "AutorEntity [id=" + id + ", nome=" + nome + ", info=" + info + ", manga=" + manga + "]";
	}
	
		
	
}
