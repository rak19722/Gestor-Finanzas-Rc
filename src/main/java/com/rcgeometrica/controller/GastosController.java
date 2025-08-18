package com.rcgeometrica.controller;

import com.rcgeometrica.entities.Gastos;
import com.rcgeometrica.entities.Obras;
import com.rcgeometrica.repository.IgastosRepository;
import com.rcgeometrica.repository.IobrasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import java.util.List;

@Controller
@RequestMapping("/obras")  // 
public class GastosController {

    @Autowired
    private IgastosRepository gastosRepository;

    @Autowired
    private IobrasRepository obrasRepository;


// Mostrar gastos con opción de búsqueda

@GetMapping("/gastosG")
public String mostrarTodosGastos(
        @RequestParam(name = "q", required = false) String q,
        Model model) {

    List<Gastos> gastos;
    if (q != null && !q.trim().isEmpty()) {
        String texto = URLDecoder.decode(q, StandardCharsets.UTF_8);
        gastos = gastosRepository.buscarPorTexto(texto.trim());
    } else {
        gastos = (List<Gastos>) gastosRepository.findAll();
    }

    model.addAttribute("gastos_generales", gastos);
    model.addAttribute("parametroBusqueda", q);
    return "Gastos_generales";
}


//gastos filtrados por obra
@GetMapping("/{obraId}/{nombre}/gastos")
public String mostrarGastosPorObra(
        @PathVariable Long obraId,
        @PathVariable String nombre,
        Model model) {

    System.out.println("Obra ID: " + obraId);
    System.out.println("Nombre obra: " + nombre);

    List<Gastos> gastos = gastosRepository.findByObras_ObraIdAndNombre(obraId, nombre);

    System.out.println("Gastos encontrados: " + gastos.size());
    for (Gastos g : gastos) {
        System.out.println("Gasto: " + g.getConcepto() + " | " + g.getPresupuesto());
    }

    model.addAttribute("gastos", gastos);
    model.addAttribute("nombreObra", nombre);
    model.addAttribute("obraId", obraId);

    return "Gastos";
}



    // Mostrar formulario de nuevo gasto
@GetMapping("/nueva-gasto/{obraId}")
public String mostrarFormularioGasto(@PathVariable Long obraId, Model model) {
    System.out.println("Entrando a nueva-gasto con obraId: " + obraId);
    Obras obra = obrasRepository.findById(obraId)
                   .orElseThrow(() -> new RuntimeException("Obra no encontrada"));
    System.out.println("Obra encontrada: " + obra.getNombre());
    
    Gastos gasto = new Gastos();
    gasto.setObras(obra);

    model.addAttribute("obra", obra);
    model.addAttribute("gasto", gasto);

    return "form-gasto";
}


    // Guardar gasto sin validar duplicados
@PostMapping("/guardar-gasto")
public String guardarGasto(@ModelAttribute Gastos gasto) {
    Obras obra = obrasRepository.findById(gasto.getObras().getObraId())
                    .orElseThrow(() -> new RuntimeException("Obra no encontrada"));
      gasto.setObras(obra);
    gasto.setNombre(obra.getNombre());  // 👈 Aquí rellenas el campo requerido

    gastosRepository.save(gasto);

    Long obraId = obra.getObraId();
    return "redirect:/obras/" + obraId + "/" + obra.getNombre() + "/gastos";
}

// editar gastos filtrados por obra
@GetMapping("/{obraId}/{nombre}/gastos/editarG/{gastoId}")
public String mostrarEditarGasto(
        @PathVariable Long obraId,
        @PathVariable String nombre,
        @PathVariable Long gastoId,
        Model model) {

    Obras obra = obrasRepository.findById(obraId)
            .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

    Gastos gasto = gastosRepository.findById(gastoId)
            .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));

    model.addAttribute("obra", obra);
    model.addAttribute("gasto", gasto);

    return "form-editar-gasto";
}


// Guardar gasto editado

@PostMapping("/{obraId}/{nombre}/gastos/editarG/{gastoId}")
public String actualizarGasto(
        @PathVariable long obraId,
        @PathVariable String nombre,
        @PathVariable long gastoId,
        @ModelAttribute Gastos gastoActualizado) {

    Gastos gasto = gastosRepository.findById(gastoId)
            .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));

    // Actualizar los campos
    gasto.setCategoriaMaterial(gastoActualizado.getCategoriaMaterial());
    gasto.setTrabajadorEncargo(gastoActualizado.getTrabajadorEncargo());
    gasto.setConcepto(gastoActualizado.getConcepto());
    gasto.setFecha(gastoActualizado.getFecha());
    gasto.setNoSemana(gastoActualizado.getNoSemana());
    gasto.setPresupuesto(gastoActualizado.getPresupuesto());


    gastosRepository.save(gasto);

    return "redirect:/obras/" + obraId + "/" + nombre + "/gastos";
}


    


//eliminar gastos filtrados por obra

    @PostMapping("/eliminarG/{id}")
    public String eliminarGasto(
            @PathVariable long id, 
            @RequestParam String nombre,
            @RequestParam long obraId) {
            gastosRepository.deleteById(id);
            return "redirect:/obras/" + obraId + "/" + nombre + "/gastos";
        }






    
}
