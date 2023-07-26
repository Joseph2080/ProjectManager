package org.chitsa.projectmanagement.Model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "milestone_id",nullable = false)
    private Long milestoneId;
    @Column(name = "milestone_title", nullable = false)
    private String milestoneTitle;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "notes")
    private String notes;
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;
    @Column(name = "due_date", nullable = false)
    private Date dueDate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "milestone_employees",
            joinColumns = @JoinColumn(name = "milestone_id", referencedColumnName = "milestone_id"),
            inverseJoinColumns = @JoinColumn(name = "uid", referencedColumnName = "uid")
    )

    private Set<Employee> employees;
    @Column(name = "fee")
    private double fee;



}