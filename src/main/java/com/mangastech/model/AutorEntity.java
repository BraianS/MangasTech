package com.mangastech.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Autor")
public class AutorEntity implements Serializable {
		
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;	
	private String info;
	
	@JsonIgnoreProperties("autor")
	private  List<MangasEntity> manga;		
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="nome",columnDefinition="varchar(50)")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	@OneToMany(mappedBy="autor",targetEntity= MangasEntity.class, fetch = FetchType.LAZY, orphanRemoval = true)	
	 @Cascade({CascadeType.PERSIST,CascadeType.MERGE})
	public List<MangasEntity> getManga() {
		return manga;
	}
	
	public void setManga(List<MangasEntity> manga) {
		this.manga = manga;
	}
	
	public AutorEntity() {
		super();
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

	public AutorEntity(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
}
