package com.banking.clients.service;


import com.banking.clients.service.dto.ClientDTO;
import com.banking.clients.service.entity.Client;
import com.banking.clients.service.repository.ClientRepository;
import com.banking.clients.service.service.ClientServiceImpl;

import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;


@ExtendWith(MockitoExtension.class)
public class AppTest {

    @Mock
    ClientRepository repo;
    @InjectMocks
    ClientServiceImpl service;
    
    Client juan = new Client("Juan", "juanCaldera@x.com");
    Client maria = new Client("Maria", "mariaGutierrez@x.com");
    Client pedro = new Client("Pedro", "pedroAlonso@x.com");
    
    //CRUD METHOD -> CREATE
    @Test
    public void createClient_success() {
        ClientDTO dto = new ClientDTO(null, juan.getName(), juan.getEmail());
        
        when(repo.existsByEmail(dto.getEmail())).thenReturn(false);
        when(repo.save(any(Client.class))).thenAnswer(inv -> {
            Client c = inv.getArgument(0);
            
            c.setId(1L); return c;
        });
        
        ClientDTO res = service.createClient(dto);
        
        assertNotNull(res.getId());
        assertEquals("Juan", res.getName());
    }
    
    //CRUD METHOD -> READ/GET
    //GET LIST OF CLIENT
    
    @Test
    public void getListOfClients_success() {
        List<Client> listOfClients = Arrays.asList(juan, maria, pedro);
        
        when(repo.findAll()).thenReturn(listOfClients);
        
        List<ClientDTO> result = service.clientsList();
        
        assertEquals(3, result.size());
        assertEquals("Maria", result.get(1).getName());
        
    }
    
    //CRUD METHOD -> DELETE
    // DELETE BY ID
    @Test
    public void deletClient_success() {
        Long id = 1L;

        // mockear que existe
        when(repo.existsById(id)).thenReturn(true);

        // deleteById es void; por defecto no hace nada en mocks, pero se puede declarar explícitamente
        doNothing().when(repo).deleteById(id);

        // ejecutar
        service.removeClient(id);

        // verificaciones: se llamó a existsById y a deleteById
        verify(repo).existsById(id);
        verify(repo).deleteById(id);
    }
    
    //testing exception
    @Test
    public void removeClient_notFound() {
        Long id = 99L;
        
        when(repo.existsById(id)).thenReturn(Boolean.FALSE);
        
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> service.removeClient(id));
        assertEquals(404, ex.getStatusCode().value());
        assertTrue(ex.getReason().contains("Cliente no encontrado"));
        
        verify(repo).existsById(id);
        verify(repo, never()).deleteById(anyLong());
        
    }
}
