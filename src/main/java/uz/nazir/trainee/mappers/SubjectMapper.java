package uz.nazir.trainee.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.nazir.trainee.dto.request.SubjectRequest;
import uz.nazir.trainee.dto.response.SubjectResponse;
import uz.nazir.trainee.entities.Subject;

/**
 * Specification for Subject
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SubjectMapper extends GenericMapper<Subject, SubjectRequest, SubjectResponse> {

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    Subject dtoToEntity(SubjectRequest request);

    SubjectResponse entityToDto(Subject entity);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    void toEntity(SubjectRequest request, @MappingTarget Subject entity);
}
