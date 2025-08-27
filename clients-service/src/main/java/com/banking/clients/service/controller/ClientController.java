package com.banking.clients.service.controller;

import com.banking.clients.service.dto.ClientDTO;
import com.banking.clients.service.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Validated
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO dto) {
        ClientDTO created = service.createClient(dto);
        return ResponseEntity.created(URI.create("/api/clients/" + created.getId())).body(created);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<ClientDTO>> listing () {
        return ResponseEntity.ok(service.clientsList());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        service.removeClient(id);
        return ResponseEntity.noContent().build();
    }
}
