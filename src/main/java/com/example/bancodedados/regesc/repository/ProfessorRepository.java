package com.example.bancodedados.regesc.repository;

import com.example.bancodedados.regesc.orm.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {//1° parametro é a classe q estamos crinado o CRUD, 2° parametro qual o tipo da PK

}
