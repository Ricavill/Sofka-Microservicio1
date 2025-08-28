package com.sofka.sofkatest.client;

import com.sofka.sofkatest.shared.commons.PasswordUtils;
import com.sofka.sofkatest.shared.config.exceptions.EntityNotFoundException;
import com.sofka.sofkatest.shared.config.exceptions.ValidationException;
import com.sofka.sofkatest.shared.config.security.SecurityProperties;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    public final ClientRepository clientRepository;
    public final SecurityProperties securityProperties;

    public ClientService(ClientRepository clientRepository, SecurityProperties securityProperties) {
        this.clientRepository = clientRepository;
        this.securityProperties = securityProperties;
    }

    public Client getClientById(Long id) {
        return this.clientRepository.findClientById(id).orElseThrow(() -> new EntityNotFoundException("Client with id " + id + " not found"));
    }

    public Client getClientByNameAndPassword(ClientRequest clientRequest) {
        return this.clientRepository.findClientByUsername(clientRequest.getUsername())
                .orElse(null);
    }

    public Client createClient(ClientRequest clientRequest) {
        Client client = this.getClientByNameAndPassword(clientRequest);
        if (client != null) {
            throw new ValidationException("Client already exists.");
        }
        String hashedPassword = PasswordUtils.hash(clientRequest.getPassword(), securityProperties.getBcryptStrength());
        client = new Client(clientRequest);
        client.setPassword(hashedPassword);
        clientRepository.save(client);
        return client;
    }


}
