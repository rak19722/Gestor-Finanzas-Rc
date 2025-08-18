package com.rcgeometrica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rcgeometrica.entities.Gastos;


@Repository
public interface IgastosRepository extends CrudRepository<Gastos, Long> {
    @Query("SELECT g FROM Gastos g WHERE " +
       "LOWER(g.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
       "LOWER(g.categoriaMaterial) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
       "LOWER(g.trabajadorEncargo) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
       "LOWER(g.concepto) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
       "LOWER(g.fecha) LIKE LOWER(CONCAT('%', :texto, '%'))"
       )
    List<Gastos> buscarPorTexto(@Param("texto") String texto);

@Query("SELECT g FROM Gastos g WHERE g.obras.obraId = :obraId AND LOWER(g.nombre) = LOWER(:nombre)")
List<Gastos> findByObras_ObraIdAndNombre(@Param("obraId") Long obraId,
                                        @Param("nombre") String nombre);

}
