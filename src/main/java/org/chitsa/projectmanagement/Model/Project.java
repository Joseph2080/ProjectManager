package org.chitsa.projectmanagement.Model;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "project_id",nullable = false)
    private Long projectId;
    @OneToOne
    @JoinColumn(name = "project_client_id",nullable = false)
    private Client client;
    @Column(name = "project_name",nullable = false)
    private String projectName;
    @Column(name = "project_description",nullable = false)
    private String projectDescription;
    @OneToMany(targetEntity=Milestone.class)
    private Set<Milestone> milestones;

}
