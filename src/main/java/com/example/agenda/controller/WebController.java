package com.example.agenda.controller;

import com.example.agenda.model.Contacto;
import com.example.agenda.service.ContactoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    private final ContactoService contactoService;

    public WebController(ContactoService contactoService){
        this.contactoService = contactoService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("contactos", contactoService.listarContactos());
        return "index";
    }

    @GetMapping("/nuevo")
    public String nuevoContactoForm(Model model){
        model.addAttribute("contacto", new Contacto());
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardarContacto(@ModelAttribute Contacto contacto){
        contactoService.guardar(contacto);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarContacto(@PathVariable Long id, Model model){
        Contacto contacto = contactoService.obtenerPorId(id).orElse(null);
        model.addAttribute("contacto", contacto);
        return "formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarContacto(@PathVariable Long id){
        contactoService.eliminar(id);
        return "redirect:/";
    }
}
