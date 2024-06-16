package org.luca.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {


        private String name;
        private String surname;
        private String mail;

        public UserDTO( String name, String surname, String mail) {

            this.name = name;
            this.surname = surname;
            this.mail = mail;

        }

}



