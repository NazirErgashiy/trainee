package uz.nazir.trainee.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uz.nazir.trainee.dto.request.SubjectRequest;
import uz.nazir.trainee.dto.response.SubjectResponse;
import uz.nazir.trainee.services.impl.SubjectService;
import uz.nazir.trainee.validation.OnCreate;

/**
 * Subject Controller Docs available in swagger
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/subjects", produces = "application/json")
public class SubjectController {

    private final SubjectService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<SubjectResponse> readPaged(
            @PageableDefault(page = 0, size = 50)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        return service.readAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubjectResponse readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectResponse create(@RequestBody
                                  @Validated(OnCreate.class)
                                  SubjectRequest request) {
        return service.create(request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id,
                       @RequestBody
                       @Validated
                       SubjectRequest request) {
        service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
