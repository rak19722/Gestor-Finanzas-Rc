package com.rcgeometrica.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rcgeometrica.entities.Obras;

@Repository
public interface IobrasRepository extends CrudRepository<Obras, Long> {
    Optional<Obras> findByNombre(String nombre);
}
