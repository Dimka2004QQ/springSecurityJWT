package ru.dimka.springSecurity.Dto;

import lombok.Data;

@Data
public class JwtRequests {
    private String username;
    private String password;
}
