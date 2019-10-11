package com.thesis.recommenderapp.domain;

import lombok.Data;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity
@Inheritance
@DiscriminatorColumn(name = "ITEM_TYPE")
@Data
public abstract class Item {

    @Id
    @GeneratedValue
    Long id;
    String title;
    String description;

    @Override
    public String toString() {
        return "Item{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
