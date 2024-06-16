package org.luca.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String city;


    public House(Long id, String name, String address, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public House() {

    }
}
