package uz.nazir.trainee.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.nazir.trainee.dto.request.TeacherRequest;
import uz.nazir.trainee.dto.response.TeacherResponse;
import uz.nazir.trainee.entities.Teacher;
import uz.nazir.trainee.error.exceptions.SubjectNotFoundException;
import uz.nazir.trainee.error.exceptions.TeacherNotFoundException;
import uz.nazir.trainee.mappers.TeacherMapper;
import uz.nazir.trainee.repositories.SubjectRepository;
import uz.nazir.trainee.repositories.TeacherRepository;
import uz.nazir.trainee.services.BaseService;

/**
 * Service for Teacher
 */
@Service
@RequiredArgsConstructor
public class TeacherService implements BaseService<TeacherRequest, TeacherResponse, Long> {

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    private final SubjectRepository subjectRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<TeacherResponse> readAll(Pageable pageable) {
        Page<Teacher> page = teacherRepository.findAll(pageable);
        return page.map(teacherMapper::entityToDto);
    }

    @Transactional(readOnly = true)
    @Override
    public TeacherResponse readById(Long id) {
        Teacher result = teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
        return teacherMapper.entityToDto(result);
    }

    @Transactional
    @Override
    public TeacherResponse create(TeacherRequest createRequest) {
        //Check before persist
        isSubjectExists(createRequest.getSubjectId());

        //Persist
        Teacher studentRequest = teacherMapper.dtoToEntity(createRequest);
        Teacher saved = teacherRepository.save(studentRequest);
        return teacherMapper.entityToDto(saved);
    }

    @Transactional
    @Override
    public void update(Long id, TeacherRequest updateRequest) {
        teacherRepository.findById(id)
                .map(student -> {
                    //Check before update
                    isSubjectExists(updateRequest.getSubjectId());

                    //Update
                    teacherMapper.toEntity(updateRequest, student);
                    Teacher savedStudent = teacherRepository.save(student);
                    return teacherMapper.entityToDto(savedStudent);
                }).orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
        } else {
            throw new TeacherNotFoundException(id);
        }
    }

    private void isSubjectExists(Long subjectId) {
        if (subjectId == null) {
            return;
        }

        if (!subjectRepository.existsById(subjectId)) {
            throw new SubjectNotFoundException(subjectId);
        }
    }
}
