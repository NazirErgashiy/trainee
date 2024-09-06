package uz.nazir.trainee.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.nazir.trainee.dto.request.TeacherRequest;
import uz.nazir.trainee.dto.response.TeacherResponse;
import uz.nazir.trainee.entities.Teacher;
import uz.nazir.trainee.mappers.util.TeacherHelper;

/**
 * Specification for Teacher
 */
@Mapper(componentModel = "spring", uses = {TeacherHelper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeacherMapper extends GenericMapper<Teacher, TeacherRequest, TeacherResponse> {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "subjectId", target = "subject")
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    Teacher dtoToEntity(TeacherRequest request);

    TeacherResponse entityToDto(Teacher entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "subjectId", target = "subject")
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    void toEntity(TeacherRequest request, @MappingTarget Teacher entity);
}
