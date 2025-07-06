package com.biblio.service;

import com.biblio.model.TypePret;
import com.biblio.repository.TypePretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypePretService {

    @Autowired
    private TypePretRepository typePretRepository;

    public List<TypePret> findAll() {
        return typePretRepository.findAll();
    }

    public TypePret findById(Integer id) {
        return typePretRepository.findById(id).orElse(null);
    }

    public TypePret save(TypePret typePret) {
        return typePretRepository.save(typePret);
    }

    public void deleteById(Integer id) {
        typePretRepository.deleteById(id);
    }
}
