package com.javaMultiThreads.contextSave.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "postgres_data")
public class PostgresData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
}

