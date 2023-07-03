package cns.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;

    @NotBlank(message = "Please Add Project Name")
    private String projectName;

    //    @NotEmpty(message = "Please Add Project Owner")
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "owner_id", nullable = true)
    private User owner;

    @NotBlank(message = "Please Add Project Intro")
    private String projectIntro;

    @NotEmpty(message = "Please Add Project Status")
    private String status;

    //    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    //    @NotEmpty(message = "Please Add Project End Date")
//    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "project_user_map",
            joinColumns = @JoinColumn(
                    name = "project_id",
                    referencedColumnName = "projectId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "userId"
            )
    )
    private Set<User> users;


}
