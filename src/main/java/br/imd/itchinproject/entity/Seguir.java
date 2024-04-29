package br.imd.itchinproject.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Seguir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Usuario follower; // Usuário que está seguindo
    
    @ManyToOne
    @JoinColumn(name = "following_id")
    private Usuario following; // Usuário seguido

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getFollower() {
		return follower;
	}

	public void setFollower(Usuario follower) {
		this.follower = follower;
	}

	public Usuario getFollowing() {
		return following;
	}

	public void setFollowing(Usuario following) {
		this.following = following;
	}
    
    // getters e setters
    
}
