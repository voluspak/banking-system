package com.banking.clients.service;


import com.banking.clients.service.dto.ClientDTO;
import com.banking.clients.service.entity.Client;
import com.banking.clients.service.repository.ClientRepository;
import com.banking.clients.service.service.ClientServiceImpl;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class AppTest {

    @Mock
    ClientRepository repo;
    @InjectMocks
    ClientServiceImpl service;
    
    @Test
    public void createClient_success() {
        ClientDTO dto = new ClientDTO(null, "Juan", "juan@x.com");
        
        when(repo.existsByEmail(dto.getEmail())).thenReturn(false);
        when(repo.save(any(Client.class))).thenAnswer(inv -> {
            Client c = inv.getArgument(0);
            
            c.setId(1L); return c;
        });
        
        ClientDTO res = service.createClient(dto);
        
        assertNotNull(res.getId());
        assertEquals("Juan", res.getName());
    }
}
