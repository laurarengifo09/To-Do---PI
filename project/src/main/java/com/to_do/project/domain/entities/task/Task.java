package com.to_do.project.domain.entities.task;

import com.to_do.project.domain.base.BaseEntity;
import com.to_do.project.domain.entities.auth.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter
@Setter
public class Task extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column()
    private String description;

    @Column(nullable = false)
    private Date dueDate;

    @Column(nullable = false)
    private String priority;

    @Column(nullable = false)
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(String title, String description, Date dueDate, String priority, User user) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.done = false;
        this.user = user;
    }
}
