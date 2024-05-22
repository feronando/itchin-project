package br.imd.itchinproject.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Feed {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "usuario_id") // renomeie para evitar confusão
    private Long usuarioId; // ID do usuário dono do feed

    @ManyToMany
    private List<Postagem> postagens;

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setUsuarioId(Long id) {
		this.usuarioId = id;
	}

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}
	
	public void addPostagem(Postagem postagem) {
        if (this.postagens == null) {
            this.postagens = new ArrayList<>();
        }
        if (!this.postagens.contains(postagem)) {
            this.postagens.add(postagem);
        }
    }
	
	public void removerPostagem(Postagem postagem) {
        if (this.postagens != null) {
            this.postagens.remove(postagem);
        }
    }

}
