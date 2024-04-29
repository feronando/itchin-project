package br.imd.itchinproject.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String imageUrl; // URL da imagem, se houver
    private String videoUrl; // URL do vídeo, se houver
    private int likes; // Quantidade de curtidas
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario user; // Usuário que fez o post

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
    
    // getters e setters
    
}
