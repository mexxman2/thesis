package com.thesis.recommenderapp.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

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

}
