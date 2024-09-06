package uz.nazir.trainee.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.nazir.trainee.dto.request.SubjectRequest;
import uz.nazir.trainee.dto.response.SubjectResponse;
import uz.nazir.trainee.entities.Subject;
import uz.nazir.trainee.error.exceptions.SubjectNotFoundException;
import uz.nazir.trainee.mappers.SubjectMapper;
import uz.nazir.trainee.repositories.SubjectRepository;
import uz.nazir.trainee.services.BaseService;

/**
 * Service for Subject
 */
@Service
@RequiredArgsConstructor
public class SubjectService implements BaseService<SubjectRequest, SubjectResponse, Long> {

    private final SubjectRepository subjectRepository;

    private final SubjectMapper subjectMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<SubjectResponse> readAll(Pageable pageable) {
        Page<Subject> page = subjectRepository.findAll(pageable);
        return page.map(subjectMapper::entityToDto);
    }

    @Transactional(readOnly = true)
    @Override
    public SubjectResponse readById(Long id) {
        Subject result = subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException(id));
        return subjectMapper.entityToDto(result);
    }

    @Transactional
    @Override
    public SubjectResponse create(SubjectRequest createRequest) {
        Subject studentRequest = subjectMapper.dtoToEntity(createRequest);
        Subject saved = subjectRepository.save(studentRequest);
        return subjectMapper.entityToDto(saved);
    }

    @Transactional
    @Override
    public void update(Long id, SubjectRequest updateRequest) {
        subjectRepository.findById(id)
                .map(student -> {
                    subjectMapper.toEntity(updateRequest, student);
                    Subject savedStudent = subjectRepository.save(student);
                    return subjectMapper.entityToDto(savedStudent);
                }).orElseThrow(() -> new SubjectNotFoundException(id));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
        } else {
            throw new SubjectNotFoundException(id);
        }
    }
}
