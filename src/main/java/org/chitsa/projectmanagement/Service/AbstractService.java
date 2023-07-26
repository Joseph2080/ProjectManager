package org.chitsa.projectmanagement.Service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public abstract class AbstractService<R extends CrudRepository, O> {
    protected R repo;

    public AbstractService(R repo) {
        this.repo = repo;
    }

    public O save(O o) {

        repo.save(o);
        return o;
    }


    public String deleteById(Long id) {
        if (repo.findById(id).isPresent()) {
            repo.deleteById(id);
            return id + " has been deleted.";
        } else {
            return id + "does not exist.";
        }
    }

    public O findById(Long id) {
        Optional<O> optionalO = repo.findById(id);
        if (optionalO.isPresent()) {
            return optionalO.get();
        }
        return null;
    }

    public List<O> getAll() {
        return (List<O>) repo.findAll();
    }

}
