package org.chitsa.projectmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import java.util.Set;


@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    private Long projectId;
    @ManyToOne
    @JoinColumn(name = "project_client_id", nullable = false)
    private Client client;
    @Column(name = "project_name", nullable = false)
    private String projectName;
    @Column(name = "project_description", nullable = false)
    private String projectDescription;
    @OneToMany(targetEntity = Milestone.class)
    private Set<Milestone> milestones;
}