package com.to_do.project.domain.entities.task;

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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column()
    private String description;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private String priority;

    @Column(nullable = false)
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(String title, String description, Date startDate, Date endDate, String priority, boolean done) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.done = done;
    }
}
