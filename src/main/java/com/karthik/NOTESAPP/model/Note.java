package com.karthik.NOTESAPP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteid;
    private String title;
    private String content;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @PrePersist
    public void Oncreate()
    {
        this.createdat = LocalDateTime.now();
        this.updatedat= LocalDateTime.now();
    }
    @PreUpdate
    public void Onupdate()
    {
        this.updatedat=LocalDateTime.now();
    }
}
