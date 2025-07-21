package com.example.agenda.controller;

import com.example.agenda.model.Contacto;
import com.example.agenda.service.ContactoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactos")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService contactoService){
        this.contactoService = contactoService;
    }

    @GetMapping
    public List<Contacto> listarContactos(){
        return contactoService.listarContactos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contacto> obtenerPorId(@PathVariable Long id){
        return contactoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contacto crear(@RequestBody Contacto contacto){
        return contactoService.guardar(contacto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contacto> actualizar(@PathVariable Long id, @RequestBody Contacto contacto){
        return contactoService.obtenerPorId(id)
                .map( c -> {
                    c.setNombre(contacto.getNombre());
                    c.setTelefono(contacto.getTelefono());
                    c.setEmail(contacto.getEmail());
                    c.setDireccion(contacto.getDireccion());
                    return ResponseEntity.ok(contactoService.guardar(c));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (contactoService.obtenerPorId(id).isPresent()){
            contactoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
