package com.lojaveiculos.crud_veiculos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErroController {

    @GetMapping("/acesso-negado")
    public String acessoNegado(Model model) {
        model.addAttribute("erro", "Login incorreto");
        return "erro";
    }
}
