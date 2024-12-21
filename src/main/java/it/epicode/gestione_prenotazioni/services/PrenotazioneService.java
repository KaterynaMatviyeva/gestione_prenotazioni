package it.epicode.gestione_prenotazioni.services;

import it.epicode.gestione_prenotazioni.entity.Postazione;
import it.epicode.gestione_prenotazioni.entity.Prenotazione;
import it.epicode.gestione_prenotazioni.entity.Utente;
import it.epicode.gestione_prenotazioni.enums.TipoPostazione;
import it.epicode.gestione_prenotazioni.repositories.PostazioneRepository;
import it.epicode.gestione_prenotazioni.repositories.PrenotazioneRepository;
import it.epicode.gestione_prenotazioni.repositories.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final PostazioneRepository postazioneRepository;
    private final UtenteRepository utenteRepository;

    public List<Postazione> cercaPostazioni(TipoPostazione tipo, String citta) {
        return postazioneRepository.findByTipoAndEdificio_Citta(tipo, citta);
    }

    public String prenotaPostazione(Long utenteId, Long postazioneId, LocalDate data) {
        Optional<Utente> utente = utenteRepository.findById(utenteId);
        Optional<Postazione> postazione = postazioneRepository.findById(postazioneId);

        if (utente.isEmpty() || postazione.isEmpty()) {
            return "Utente o Postazione non trovati.";
        }

        if (prenotazioneRepository.existsByPostazioneAndData(postazione.get(), data)) {
            return "La postazione non è disponibile per la data selezionata.";
        }

        if (!prenotazioneRepository.findByUtenteAndData(utente.get(), data).isEmpty()) {
            return "L'utente ha già una prenotazione per questa data.";
        }

        Prenotazione prenotazione = new Prenotazione(null, data, utente.get(), postazione.get());
        prenotazioneRepository.save(prenotazione);
        return "Prenotazione effettuata con successo.";
    }
}