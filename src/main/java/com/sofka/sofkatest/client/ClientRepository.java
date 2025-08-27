package com.sofka.sofkatest.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c " +
            "from Client c " +
            "where c.deletedAt is null")
    List<Client> findAllClients();
}
