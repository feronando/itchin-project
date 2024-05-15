package br.imd.itchinproject.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    
    @OneToMany(mappedBy = "following")
    private List<Seguir> followers; // Seguidores do usuário
    
    @OneToMany(mappedBy = "follower")
    private List<Seguir> following; // Usuários seguidos
    
    @OneToMany(mappedBy = "user")
    private List<Postagem> posts; // Publicações do usuário
    
    @OneToMany(mappedBy = "user")
    private List<Jogo> games; // Jogos postados pelo usuário
    
    @ManyToMany
    @JoinTable(
        name = "user_favorite_games",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Jogo> favoriteGames;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Seguir> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Seguir> followers) {
		this.followers = followers;
	}

	public List<Seguir> getFollowing() {
		return following;
	}

	public void setFollowing(List<Seguir> following) {
		this.following = following;
	}

	public List<Postagem> getPosts() {
		return posts;
	}

	public void setPosts(List<Postagem> posts) {
		this.posts = posts;
	}

	public List<Jogo> getGames() {
		return games;
	}

	public void setGames(List<Jogo> games) {
		this.games = games;
	}

	public List<Jogo> getFavoriteGames() {
		return favoriteGames;
	}

	public void setFavoriteGames(List<Jogo> favoriteGames) {
		this.favoriteGames = favoriteGames;
	}
} 
