package com.multiThreads.threadExample.entity.postgresql;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "thread_string")
public class PostgreSqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
}
