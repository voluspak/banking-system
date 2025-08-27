package com.banking.clients.service.service;

import com.banking.clients.service.dto.ClientDTO;
import java.util.List;

public interface ClientService {
    
    ClientDTO createClient(ClientDTO dto);
    ClientDTO getById(Long id);
    List<ClientDTO> clientsList();
    void removeClient(Long id);

}
