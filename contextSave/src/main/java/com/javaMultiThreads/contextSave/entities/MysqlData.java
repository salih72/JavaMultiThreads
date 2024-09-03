package com.javaMultiThreads.contextSave.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mysql_data")
public class MysqlData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
}
