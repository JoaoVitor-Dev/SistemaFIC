package com.TDD.Sistema.service;
import com.TDD.Sistema.model.repository.DadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CursoService {
    @Autowired
    private DadosRepository dadosRepository;
}
