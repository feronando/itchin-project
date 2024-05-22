package br.imd.itchinproject.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    
    @ElementCollection
    private List<Long> followers; // IDs dos seguidores do usuário
    
    @ElementCollection
    private List<Long> following; // IDs dos usuários seguidos

    @ElementCollection
    private List<Long> posts; // Publicações do usuário
    
    @OneToMany(mappedBy = "user")
    private List<Jogo> games; // Jogos postados pelo usuário
    
    private Long feedId; // ID do feed do usuário

    @ManyToMany
    @JoinTable(
        name = "user_favorite_games",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Jogo> favoriteGames = new ArrayList<>();

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

    public List<Long> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Long> followers) {
        this.followers = followers;
    }

    public List<Long> getFollowing() {
        return following;
    }

    public void setFollowing(List<Long> following) {
        this.following = following;
    }

    public List<Long> getPosts() {
        return posts;
    }

    public void setPosts(List<Long> posts) {
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

    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }
    
    public void addFollower(Long followerId) {
        if (this.followers == null) {
            this.followers = new ArrayList<>();
        }
        if (!this.followers.contains(followerId)) {
            this.followers.add(followerId);
        }
    }

    public void removeFollower(Long followerId) {
        if (this.followers != null) {
            this.followers.remove(followerId);
        }
    }

    public void addFollowing(Long followingId) {
        if (this.following == null) {
            this.following = new ArrayList<>();
        }
        if (!this.following.contains(followingId)) {
            this.following.add(followingId);
        }
    }

    public void removeFollowing(Long followingId) {
        if (this.following != null) {
            this.following.remove(followingId);
        }
    }

    public void addPost(Long postId) {
        if (this.posts == null) {
            this.posts = new ArrayList<>();
        }
        this.posts.add(postId);
    }

    public void removePost(Long postId) {
        if (this.posts != null) {
            this.posts.remove(postId);
        }
    }
}
