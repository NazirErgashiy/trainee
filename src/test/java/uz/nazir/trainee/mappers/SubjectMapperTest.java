package uz.nazir.trainee.mappers;

import org.junit.jupiter.api.Test;
import uz.nazir.trainee.dto.request.SubjectRequest;
import uz.nazir.trainee.entities.Subject;

import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SubjectMapperTest {

    private final SubjectMapper mapper = new SubjectMapperImpl();

    @Test
    void dtoToEntity() {
        var expected = SubjectRequest.builder()
                .name("John")
                .build();

        var result = mapper.dtoToEntity(expected);

        assertEquals(expected.getName(), result.getName());

        assertNull(result.getId());
        assertNull(result.getCreateDate());
        assertNull(result.getUpdateDate());
    }

    @Test
    void entityToDto() {
        LocalDateTime now = LocalDateTime.now();

        var expected = Subject.builder()
                .id(1L)
                .name("John")
                .createDate(now)
                .updateDate(now)
                .build();

        var result = mapper.entityToDto(expected);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getCreateDate(), result.getCreateDate());
        assertEquals(expected.getUpdateDate(), result.getUpdateDate());
    }

    @Test
    void toEntity() {

        var expected = SubjectRequest.builder()
                .name("John")
                .build();

        LocalDateTime now = LocalDateTime.now();

        var result = Subject.builder()
                .id(1L)
                .name("John")
                .createDate(now)
                .updateDate(now)
                .build();

        mapper.toEntity(expected, result);

        assertEquals(expected.getName(), result.getName());

        assertNotNull(result.getId());
        assertNotNull(result.getCreateDate());
        assertNotNull(result.getUpdateDate());
    }
}
