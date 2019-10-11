package com.thesis.recommenderapp.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @NotEmpty
    private String userName;
    @NotNull
    @NotEmpty
    private String password;
    private boolean enabled;
    @OneToMany
    private List<Watched> watched;
    @OneToMany
    private List<User> friends;

    public void addToWatched(Watched watch) {
        watched.add(watch);
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public void updateWatched(int index, Watched watched) {
        this.watched.set(index, watched);
    }

}
