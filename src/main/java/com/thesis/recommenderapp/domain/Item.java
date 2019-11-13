package com.thesis.recommenderapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue
    Long id;
    @JsonProperty("Title")
    String title;
    @JsonProperty("Plot")
    String description;
    @JsonProperty("Year")
    String year;
    @JsonProperty("Runtime")
    String runtime;
    @JsonProperty("Genre")
    String genre;
    @JsonProperty("imdbRating")
    String imdbRating;
    @JsonProperty("imdbID")
    String imdbId;
    @JsonProperty("Poster")
    String posterPath;
    @JsonProperty("Type")
    String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
            Objects.equals(imdbId, item.imdbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imdbId);
    }

}
