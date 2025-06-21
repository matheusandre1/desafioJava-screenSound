package com.example.screenSound.repository;

import com.example.screenSound.models.Artista;
import com.example.screenSound.models.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long>
{
    Optional<Artista> findByNomeContainingIgnoreCase(String nome);


}
