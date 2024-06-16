package org.luca.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<House> houses = new ArrayList<>();


    public void addHouse(House house) {
        this.houses.add(house);
    }

    public void removeHouse(House house) {
        this.houses.remove(house);
    }


    public Users(Long id, String name, String surname, String mail, String password, List<House> houses) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.houses = houses;
    }

    public Users(Long id, String name, String surname, String mail, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
    }

//    public Users( String name, String surname, String mail) {
//        this.name = name;
//        this.surname = surname;
//        this.mail = mail;
//
//    }

}
