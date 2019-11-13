package com.thesis.recommenderapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("movie")
@Data
@SuperBuilder
@NoArgsConstructor
public class Movie extends Item {
}
