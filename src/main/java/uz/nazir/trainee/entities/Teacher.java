package uz.nazir.trainee.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static uz.nazir.trainee.util.StandardTime.nowIso8601;

/**
 * Teacher Table...
 * <div>Relations:</div>
 * <div>ManyToOne Many(Teachers) can have One(Subject)</div>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_teachers")
@NamedEntityGraph(name = "Teacher.subject",
        attributeNodes = @NamedAttributeNode("subject"))
public class Teacher {

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
     * Relation: ManyToOne Many(Teachers) can have One(Subject)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

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
