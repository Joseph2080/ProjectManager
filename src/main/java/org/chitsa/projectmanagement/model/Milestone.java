package org.chitsa.projectmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "milestones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_id", nullable = false)
    private Long milestoneId;
    @Column(name = "milestone_title", nullable = false)
    private String milestoneTitle;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;
    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "milestone_employees",
            joinColumns = @JoinColumn(name = "milestone_id", referencedColumnName = "milestone_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    )
    private Set<Employee> employees;
    @Column(name = "fee")
    private double fee;
}
