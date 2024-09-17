package com.ebgr.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private String text;
}
