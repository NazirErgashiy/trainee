package uz.nazir.trainee.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import uz.nazir.trainee.entities.Teacher;

import java.util.Optional;

/**
 * Repository for table "_TEACHERS"
 */
@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long>, PagingAndSortingRepository<Teacher, Long> {

    /**
     * Override methods with EntityGraph
     * Solves n+1 problem
     */
    @Override
    @NonNull
    @EntityGraph(value = "Teacher.subject")
    Page<Teacher> findAll(Pageable pageable);

    /**
     * Override methods with EntityGraph
     * Solves n+1 problem
     */
    @Override
    @NonNull
    @EntityGraph(value = "Teacher.subject")
    Optional<Teacher> findById(Long id);
}
