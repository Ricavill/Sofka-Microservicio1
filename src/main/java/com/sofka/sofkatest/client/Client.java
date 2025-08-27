package com.sofka.sofkatest.client;

import com.sofka.sofkatest.person.Person;
import com.sofka.sofkatest.shared.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;

/* En el documento se pide que se herede de persona(es una entidad), esto por JPA le dara mismo id(PK) de
persona a cliente,pero ademas tambien piden que tenga clientId(no es claro en el documento si es un id propio o no),
rompiendo el principio de herencia y degradando las practicas, lo que es confuso. Debido a esto se aplicara
como metodo la herencia como se dijo en vez de composition.*/
@Entity
@Table(name = "user")
@DynamicUpdate
public class Client extends Person {

    @NotNull
    @Size(max = 60)
    @Column(name = "password_hash")
    private String password;

    //Usualmente se toma 0 como inactivo y 1 como activo, pero prefiero ser especifico.
    private Status status;


}
