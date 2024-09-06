package uz.nazir.trainee.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static uz.nazir.trainee.util.StandardTime.nowIso8601;

/**
 * Entity
 * No relations
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_subjects")
public class Subject {

    /**
     * ID column
     * Generated with IDENTITY strategy
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

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
