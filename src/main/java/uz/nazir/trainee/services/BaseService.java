package uz.nazir.trainee.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Base service
 *
 * @param <T> Request DTO
 * @param <R> Response DTO
 * @param <K> ID
 */
public interface BaseService<T, R, K> {

    Page<R> readAll(Pageable pageable);

    R readById(K id);

    R create(T createRequest);

    void update(K id, T updateRequest);

    void deleteById(K id);
}
