package com.example.repository;

import com.example.model.Salle;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface SalleRepository {

    // Méthode pour trouver les salles disponibles par créneau
    List<Salle> findAvailableRooms(LocalDateTime start, LocalDateTime end);

    // Méthode pour la recherche multi-critères
    List<Salle> findByCriteria(Map<String, Object> criteria);

    // Méthode pour la pagination
    List<Salle> findAllPaginated(int page, int size);

    // Méthode pour compter le nombre total de salles (pour la pagination)
    long count();

    // Autres méthodes CRUD de base
    Salle findById(Long id);
    List<Salle> findAll();
    void save(Salle salle);
    void update(Salle salle);
    void delete(Salle salle);
}