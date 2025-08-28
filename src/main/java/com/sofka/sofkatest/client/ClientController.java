package com.sofka.sofkatest.client;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return this.clientService.getClientById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody @Validated ClientRequest clientRequest) {
        return this.clientService.createClient(clientRequest);
    }


}
