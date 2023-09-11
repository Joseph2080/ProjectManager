package org.chitsa.projectmanagement.service.impl;

import org.chitsa.projectmanagement.exceptions.InvalidParameterException;
import org.chitsa.projectmanagement.service.Service;
import org.chitsa.projectmanagement.util.ValidationErrors;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// T represents entity type while T1 represents DTO
public abstract class AbstractServiceImpl<R extends JpaRepository, T, T1> implements Service<T, T1> {

    protected R repo;

    public AbstractServiceImpl(R repo) {
        this.repo = repo;
    }

    @Override
    public void register(T1 dto) {
        T t = validateDto(dto);
        repo.save(dto);
    }

    @Override
    public void update(Long id, T1 dto) {
        T t = validateDto(dto);
        setId(id, t);
        repo.save(dto);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }

    @Override
    public List<T1> loadAll() {
        return repo.findAll();
    }

    @Override
    public T1 loadById(Long id) {
        Optional<T> optionalT = repo.findById(id);
        if (optionalT.isEmpty()) {
            throw new IllegalArgumentException(id + " id not found.");
        }
        return convertModelToDto(optionalT.get());
    }

    protected abstract void setId(Long id, T t);

    private T validateDto(T1 dto) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T1>> constraintViolations = validator.validate(dto);
        for (ConstraintViolation<T1> violation : constraintViolations) {
            String errorMessage = violation.getMessage();
            if (errorMessage.contains(ValidationErrors.NullField.label)
                    || errorMessage.contains(ValidationErrors.EmptyField.label)
                    || errorMessage.contains(ValidationErrors.InvalidEmail.label)) {
                throw new RuntimeException(new InvalidParameterException(errorMessage));
            }
        }
        return convertDtoToModel(dto);
    }

    protected abstract T1 convertModelToDto(T model);
    protected abstract T convertDtoToModel(T1 dto);
}