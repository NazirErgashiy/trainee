package uz.nazir.trainee.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

import static uz.nazir.trainee.util.StandardTime.nowIso8601;

/**
 * Student Table...
 * <div>Relations:</div>
 * <div>ManyToMany Many(Students) can have Many(Teachers)</div>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_students")
@NamedEntityGraph(name = "Student.teachers",
        attributeNodes = @NamedAttributeNode("teachers"))
public class Student {

    /**
     * ID column
     * Generated with IDENTITY strategy
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column
    private Integer age;

    /**
     * Relation: ManyToMany Many(Students) can have Many(Teachers)
     * Creates Table student_teachers with two columns (student_id, teacher_id)
     */
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_teachers",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id")}
    )
    private List<Teacher> teachers;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    /**
     * Automatically update time before persist and update
     */
    @PrePersist
    @PreUpdate
    public void prePersistOrUpdate() {
        updateDate = nowIso8601();
        if (createDate == null) {
            createDate = updateDate;
        }
    }
}
