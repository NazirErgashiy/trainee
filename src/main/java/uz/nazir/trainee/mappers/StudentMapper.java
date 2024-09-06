package uz.nazir.trainee.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.nazir.trainee.dto.request.StudentRequest;
import uz.nazir.trainee.dto.response.StudentResponse;
import uz.nazir.trainee.entities.Student;
import uz.nazir.trainee.mappers.util.StudentHelper;

/**
 * Specification for Student
 */
@Mapper(componentModel = "spring", uses = {StudentHelper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper extends GenericMapper<Student, StudentRequest, StudentResponse> {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "teacherIds", target = "teachers")
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    Student dtoToEntity(StudentRequest request);

    StudentResponse entityToDto(Student entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "teacherIds", target = "teachers")
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    void toEntity(StudentRequest request, @MappingTarget Student entity);
}
