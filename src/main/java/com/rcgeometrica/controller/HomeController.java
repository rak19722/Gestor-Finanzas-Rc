package com.rcgeometrica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

@GetMapping("/")
public String index() {
    return "redirect:/obras"; // Reutiliza el método de ObrasController
}

    @GetMapping("gastos")
    public String gastos() {
        return "Gastos"; // Renderiza templates/Gastos.html
    }
}


