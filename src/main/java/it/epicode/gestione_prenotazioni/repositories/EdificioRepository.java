package it.epicode.gestione_prenotazioni.repositories;

import it.epicode.gestione_prenotazioni.entity.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Long> {
}