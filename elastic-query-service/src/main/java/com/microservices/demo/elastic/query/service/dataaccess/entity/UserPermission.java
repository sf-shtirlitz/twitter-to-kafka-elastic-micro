package com.microservices.demo.elastic.query.service.dataaccess.entity;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
public class UserPermission {

    @NotNull
    @Id
    private UUID id;

    @NotNull
    private String username;

    @NotNull
    private String documentId;

    @NotNull
    private String permissionType;
}
