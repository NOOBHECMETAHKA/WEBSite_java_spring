package com.example.springmodels.repos;

import com.example.springmodels.models.*;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ModelUser,Long> {
    ModelUser findByUsername(String username);
}
