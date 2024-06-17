package org.luca.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
public class UserDTO {


        private String name;
        private String surname;
        private String mail;
    private List<OrderDTO> orders;

}



