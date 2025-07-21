package com.example.agenda.service;

import com.example.agenda.model.Contacto;
import com.example.agenda.repository.ContactoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoService {

    private final ContactoRepository contactoRepository;

    public ContactoService(ContactoRepository contactoRepository){
        this.contactoRepository = contactoRepository;
    }

    public List<Contacto> listarContactos(){
        return contactoRepository.findAll();
    }

    public Optional<Contacto> obtenerPorId(Long id){
        return contactoRepository.findById(id);
    }

    public Contacto guardar(Contacto contacto){
        return contactoRepository.save(contacto);
    }

    public void eliminar(Long id){
        contactoRepository.deleteById(id);
    }
}
