package com.sofka.sofkatest.client;

import com.sofka.sofkatest.person.Person;
import com.sofka.sofkatest.shared.commons.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Map;

/* En el documento se pide que se herede de persona(es una entidad), esto por JPA le dara mismo id(PK) de
persona a cliente,pero ademas tambien piden que tenga clientId(no es claro en el documento si es un id propio o no),
rompiendo el principio de herencia y degradando las practicas, lo que es confuso. Debido a esto se aplicara
como metodo la herencia como se dijo en vez de composition.*/
@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class Client extends Person {

    //todo pensar si es buena idea poner unique
    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(max = 60)
    @Column(name = "password_hash")
    private String password;

    //Usualmente se toma 0 como inactivo y 1 como activo, pero prefiero ser especifico.
    private Status status;

    public Client() {
    }

    public Client(ClientRequest clientRequest) {
        super(clientRequest);
        this.username = clientRequest.getUsername();
        this.password = clientRequest.getHashedPassword();
        this.status = Status.ACTIVE;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static Map<String, Object> toUserData(Client client) {
        Map<String, Object> data = Client.toPersonData(client);
        data.put("username", client.getUsername());
        data.put("status", client.getStatus());
        return data;
    }

}
