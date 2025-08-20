package com.rcgeometrica.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "obras")
public class Obras {

    @OneToMany(mappedBy = "obras", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gastos> gastos;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "obra_id")
    private Long obraId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "maestro_encargado")
    private String maestroEncargado;

    @Column(name = "cliente")
    private String cliente;

    @Column(name = "tipo_obra")
    private String tipoObra;

   
    @Column(name = "fecha_inicio")
    private String fechaInicio;

    
    @Column(name = "fecha_finalizacion")
    private String fechaFinalizacion;

    

    @Column(name = "presupuesto")
    private BigDecimal presupuesto;
}
