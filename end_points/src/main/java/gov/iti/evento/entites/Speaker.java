package gov.iti.evento.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "speaker")
public class Speaker implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Size(max = 45)
    @Column(name = "job_title", length = 45)
    private String jobTitle;

    @Size(max = 45)
    @Column(name = "description", length = 45)
    private String description;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @Size(max = 45)
    @Column(name = "image", length = 45)
    private String image;

}