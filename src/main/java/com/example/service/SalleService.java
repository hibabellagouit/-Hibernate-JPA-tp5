package com.example.service;

import com.example.model.Salle;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface SalleService {

    // Méthode pour trouver les salles disponibles par créneau
    List<Salle> findAvailableRooms(LocalDateTime start, LocalDateTime end);

    // Méthode pour la recherche multi-critères
    List<Salle> searchRooms(Map<String, Object> criteria);

    // Méthode pour la pagination
    List<Salle> getPaginatedRooms(int page, int size);

    // Méthode pour obtenir le nombre total de pages
    int getTotalPages(int size);

    // Autres méthodes de service
    Salle getRoomById(Long id);
    List<Salle> getAllRooms();
    void saveRoom(Salle salle);
    void updateRoom(Salle salle);
    void deleteRoom(Salle salle);
}