package com.sofka.sofkatest.client;

import com.sofka.sofkatest.shared.commons.PasswordUtils;
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

    public Client getClientByNameAndPassword(ClientRequest clientRequest) {
        String hashedPassword = PasswordUtils.hash(clientRequest.getPassword(), securityProperties.getBcryptStrength());
        return this.clientRepository.findClientByNameAndHashedPassword(clientRequest.getName(), hashedPassword)
                .orElse(null);
    }

    public Client createClient(ClientRequest clientRequest) {
        Client client = this.getClientByNameAndPassword(clientRequest);
        if (client != null) {
            throw new ValidationException("Client already exists.");
        }

        client = new Client(clientRequest);
        clientRepository.save(client);
        return client;
    }


}
