package it.epicode.gestione_prenotazioni.repositories;

import it.epicode.gestione_prenotazioni.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
}