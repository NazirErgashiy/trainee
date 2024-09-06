package uz.nazir.trainee.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uz.nazir.trainee.entities.Subject;

/**
 * Repository for table "_SUBJECTS"
 */
@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long>, PagingAndSortingRepository<Subject, Long> {
}
