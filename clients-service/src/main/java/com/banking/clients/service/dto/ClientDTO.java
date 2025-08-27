package com.banking.clients.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClientDTO {
    private Long id;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    
    @NotBlank(message = "Email no puede estar vacío")
    @Email(message = "Email inválido")
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
