package uz.nazir.trainee.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import uz.nazir.trainee.entities.Student;

import java.util.Optional;

/**
 * Repository for table "_STUDENTS"
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Long>, PagingAndSortingRepository<Student, Long> {

    /**
     * Override methods with EntityGraph
     * Solves n+1 problem
     */
    @Override
    @NonNull
    @EntityGraph(value = "Student.teachers")
    Page<Student> findAll(Pageable pageable);

    /**
     * Override methods with EntityGraph
     * Solves n+1 problem
     */
    @Override
    @NonNull
    @EntityGraph(value = "Student.teachers")
    Optional<Student> findById(Long id);
}
