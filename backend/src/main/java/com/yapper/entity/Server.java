package com.yapper.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="servers")
public class Server {

    @Id
    Long id;
    String name;
}
