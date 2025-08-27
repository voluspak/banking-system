package com.banking.clients.service.service;

import com.banking.clients.service.dto.ClientDTO;
import com.banking.clients.service.entity.Client;
import com.banking.clients.service.repository.ClientRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repo;

    public ClientServiceImpl(ClientRepository repo) {
        this.repo = repo;
    }
    
    private ClientDTO toDTO(Client client) {
        return new ClientDTO(client.getId(), client.getName(), client.getEmail());
    }
    
     @Override
     public ClientDTO createClient(ClientDTO dto) {
         if (repo.existsByEmail(dto.getEmail())) {
             throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ya registrado");
         }
         
         Client client = new Client(dto.getName(), dto.getEmail());
         Client saved = repo.save(client);
         
         return toDTO(saved);
     }
     
    @Override
    public ClientDTO getById(Long id) {
        Client c = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        return toDTO(c);
    }
    
    @Override
    public List<ClientDTO> clientsList() {
        return repo.findAll().stream().map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public void removeClient(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }
        repo.deleteById(id);
    }
}
