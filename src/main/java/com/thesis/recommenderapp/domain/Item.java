package com.thesis.recommenderapp.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance
@DiscriminatorColumn(name = "ITEM_TYPE")
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Item {

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

}
