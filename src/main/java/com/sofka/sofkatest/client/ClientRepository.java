package com.sofka.sofkatest.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c " +
            "from Client c " +
            "where c.deletedAt is null")
    List<Client> findAllClients();

    @Query("select c " +
            "from Client c " +
            "where c.name=?1 " +
            "and c.password=?2 " +
            "and  c.deletedAt is null")
    Optional<Client> findClientByNameAndHashedPassword(String username, String hashedPassword);
}
