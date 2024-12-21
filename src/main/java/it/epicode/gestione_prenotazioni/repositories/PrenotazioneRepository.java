package it.epicode.gestione_prenotazioni.repositories;

import it.epicode.gestione_prenotazioni.entity.Postazione;
import it.epicode.gestione_prenotazioni.entity.Prenotazione;
import it.epicode.gestione_prenotazioni.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByUtenteAndData(Utente utente, LocalDate data);
    boolean existsByPostazioneAndData(Postazione postazione, LocalDate data);
}