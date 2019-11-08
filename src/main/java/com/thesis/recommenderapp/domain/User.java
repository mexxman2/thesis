package com.thesis.recommenderapp.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "reco_user")
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
    private List<Watched> watched = new ArrayList<>();
    @OneToMany
    private Set<User> friends = new HashSet<>();

    public void addToWatched(Watched watch) {
        watched.add(watch);
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public void deleteFriend(User user) {
        friends.remove(user);
    }

    public void updateWatched(Watched watched) {
        this.watched.stream()
                .filter(myWatched -> myWatched.getItem().equals(watched.getItem()))
                .findFirst().get()
                .setRating(watched.getRating());
    }

    public void deleteWatched(Item item) {
        watched.removeIf(myWatched -> myWatched.getItem().equals(item));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
