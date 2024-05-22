package br.imd.itchinproject.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario user; // Usuário que criou o jogo
    
    @ManyToMany(mappedBy = "favoriteGames")
    private List<Usuario> likedByUsers; // Usuários que curtiram o jogo

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public List<Usuario> getLikedByUsers() {
		return likedByUsers;
	}

	public void setLikedByUsers(List<Usuario> likedByUsers) {
		this.likedByUsers = likedByUsers;
	}
    
}
