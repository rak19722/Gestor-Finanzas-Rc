package com.rcgeometrica.controller;

import com.rcgeometrica.entities.Obras;
import com.rcgeometrica.repository.IobrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/obras")
public class ObrasController {

    @Autowired
    private IobrasRepository obrasRepository;

    // Listar todas las obras
    @GetMapping
    public String mostrarObras(Model model) {
        model.addAttribute("obras", obrasRepository.findAll());
        return "index"; // Carga index.html con los datos
    }

    // Mostrar formulario para agregar obra
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaObra(Model model) {
        model.addAttribute("obra", new Obras());
        return "form-obra";
    }

    // Guardar obra
    @PostMapping
    public String guardarObra(@ModelAttribute Obras obra) {
        obrasRepository.save(obra);
        return "redirect:/obras"; // Vuelve al listado
    }


    //editar obra

        //encontrar obra atraves de id
@GetMapping("/editar/{obraId}")
    public String mostrarFormularioEditar(@PathVariable Long obraId, Model model) {
    Obras obra = obrasRepository.findById(obraId)
                     .orElseThrow(() -> new RuntimeException("Obra no encontrada"));
    model.addAttribute("obra", obra);
    return "form-editar-obra";
}



    //guardar obra modificada
@PostMapping("/editar/{obraId}")
public String actualizarObra(@PathVariable Long obraId, @ModelAttribute Obras obraActualizada) {
    Obras obra = obrasRepository.findById(obraId)
                     .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

    obra.setNombre(obraActualizada.getNombre());
    obra.setMaestroEncargado(obraActualizada.getMaestroEncargado());
    obra.setCliente(obraActualizada.getCliente());
    obra.setTipoObra(obraActualizada.getTipoObra());
    obra.setFechaInicio(obraActualizada.getFechaInicio());
    obra.setFechaFinalizacion(obraActualizada.getFechaFinalizacion());
    obra.setPresupuesto(obraActualizada.getPresupuesto());

    obrasRepository.save(obra);
    return "redirect:/obras"; // vuelve al listado
}

        

    //eliminar obra
    @PostMapping("/eliminar/{id}")
    public String eliminarObra(@PathVariable long id){
        obrasRepository.deleteById(id);
        return "redirect:/obras"; // Vuelve al listado
    }
}
