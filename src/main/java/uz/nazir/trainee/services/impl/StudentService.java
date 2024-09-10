package uz.nazir.trainee.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.nazir.trainee.dto.request.StudentRequest;
import uz.nazir.trainee.dto.response.StudentResponse;
import uz.nazir.trainee.entities.Student;
import uz.nazir.trainee.error.exceptions.StudentNotFoundException;
import uz.nazir.trainee.error.exceptions.TeacherNotFoundException;
import uz.nazir.trainee.mappers.StudentMapper;
import uz.nazir.trainee.repositories.StudentRepository;
import uz.nazir.trainee.repositories.TeacherRepository;
import uz.nazir.trainee.services.BaseService;

import java.util.List;


/**
 * Service for Student
 */
@Service
@RequiredArgsConstructor
public class StudentService implements BaseService<StudentRequest, StudentResponse, Long> {

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final StudentMapper studentMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<StudentResponse> readAll(Pageable pageable) {
        Page<Student> page = studentRepository.findAll(pageable);
        return page.map(studentMapper::entityToDto);
    }

    @Transactional(readOnly = true)
    @Override
    public StudentResponse readById(Long id) {
        Student result = studentRepository
                .findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return studentMapper.entityToDto(result);
    }

    @Transactional
    @Override
    public StudentResponse create(StudentRequest createRequest) {
        //Check before persist
        isTeachersExist(createRequest.getTeacherIds());

        //Persist
        Student studentRequest = studentMapper.dtoToEntity(createRequest);
        Student saved = studentRepository.save(studentRequest);
        return studentMapper.entityToDto(saved);
    }

    @Transactional
    @Override
    public void update(Long id, StudentRequest updateRequest) {
        studentRepository.findById(id)
                .map(student -> {
                    //Check before update
                    isTeachersExist(updateRequest.getTeacherIds());

                    //Update
                    studentMapper.toEntity(updateRequest, student);
                    Student savedStudent = studentRepository.save(student);
                    return studentMapper.entityToDto(savedStudent);
                }).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    private void isTeachersExist(List<Long> teacherIds) {
        if (teacherIds == null) {
            return;
        }

        if (!teacherIds.isEmpty()) {
            for (Long teacherId : teacherIds) {
                if (!teacherRepository.existsById(teacherId)) {
                    throw new TeacherNotFoundException(teacherId);
                }
            }
        }
    }
}
