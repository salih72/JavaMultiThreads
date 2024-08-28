package com.multiThreads.threadExample.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "thread_string")
public class MySqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;
}
