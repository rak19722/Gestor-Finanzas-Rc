package com.rcgeometrica.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gastos")
@ToString(exclude = "obras")
public class Gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gasto_id")
    private Long gastoId;

    // Serializa el ID de obra como "obra_id"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "obra_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonProperty("obra_id")
    private Obras obras;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "categoria_material")
    private String categoriaMaterial;

    @Column(name = "trabajador_encargo")
  
    private String trabajadorEncargo;

    @Column(name = "concepto")
    private String concepto;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "numero_semana")
 
    private Long NoSemana;

    @Column(name = "precio")
    private BigDecimal presupuesto;

}
