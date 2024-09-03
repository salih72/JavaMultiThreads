package com.javaMultiThreads.contextSave.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mariadb_data")
public class MariadbData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
}
