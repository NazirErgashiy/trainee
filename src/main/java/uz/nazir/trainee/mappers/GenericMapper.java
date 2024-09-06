package uz.nazir.trainee.mappers;

/**
 * SUPER Mapper for all other mappers
 *
 * @param <E> Entity
 * @param <R> Request DTO
 * @param <V> Response DTO
 */
public interface GenericMapper<E, R, V> {
    /**
     * Map Request DTO to Entity
     *
     * @param request Request DTO
     * @return Entity
     */
    E dtoToEntity(R request);

    /**
     * Map Entity to Response DTO
     *
     * @param entity Entity
     * @return Response DTO
     */
    V entityToDto(E entity);

    /**
     * Map Request DTO to Entity (Highly used for updating entity PATCH)
     *
     * @param request Source Request DTO
     * @param entity Target Entity
     */
    void toEntity(R request, E entity);
}
