package com.example.springmodels.repos;

import com.example.springmodels.models.*;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {

}
