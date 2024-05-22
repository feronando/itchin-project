package br.imd.itchinproject.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Seguir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "follower_id")
    private Long followerId; // ID do usuário que está seguindo
    
    @Column(name = "following_id")
    private Long followingId; // ID do usuário seguido

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }
    
}
