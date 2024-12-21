package it.epicode.gestione_prenotazioni.runner;

import it.epicode.gestione_prenotazioni.entity.Edificio;
import it.epicode.gestione_prenotazioni.entity.Postazione;
import it.epicode.gestione_prenotazioni.entity.Prenotazione;
import it.epicode.gestione_prenotazioni.entity.Utente;
import it.epicode.gestione_prenotazioni.enums.TipoPostazione;
import it.epicode.gestione_prenotazioni.repositories.EdificioRepository;
import it.epicode.gestione_prenotazioni.repositories.PostazioneRepository;
import it.epicode.gestione_prenotazioni.repositories.PrenotazioneRepository;
import it.epicode.gestione_prenotazioni.repositories.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DatabaseRunner {

    private final EdificioRepository edificioRepository;
    private final PostazioneRepository postazioneRepository;
    private final UtenteRepository utenteRepository;
    private final PrenotazioneRepository prenotazioneRepository;

    @Bean
    ApplicationRunner runDatabaseSeeder() {
        return args -> {
            // Creazione degli edifici
            Edificio edificio1 = new Edificio(null, "Edificio A", "Via Roma 1", "Roma");
            Edificio edificio2 = new Edificio(null, "Edificio B", "Via Milano 2", "Milano");
            edificioRepository.saveAll(List.of(edificio1, edificio2));

            // Creazione delle postazioni e aggiunta agli edifici
            Postazione postazione1 = new Postazione(null, "P001", "Postazione Privata 1", TipoPostazione.PRIVATO, 1, edificio1);
            Postazione postazione2 = new Postazione(null, "P002", "Postazione Open Space 1", TipoPostazione.OPENSPACE, 4, edificio2);
            postazioneRepository.saveAll(List.of(postazione1, postazione2));

            edificio1.getPostazioni().add(postazione1);
            edificio2.getPostazioni().add(postazione2);
            edificioRepository.saveAll(List.of(edificio1, edificio2));

            // Creazione degli utenti
            Utente utente1 = new Utente(null, "user1", "Mario Rossi", "mario.rossi@example.com", new ArrayList<>());
            Utente utente2 = new Utente(null, "user2", "Luigi Bianchi", "luigi.bianchi@example.com", new ArrayList<>());
            utenteRepository.saveAll(List.of(utente1, utente2));

            // Creazione delle prenotazioni
            Prenotazione prenotazione1 = new Prenotazione(null, LocalDate.of(2024, 12, 22), utente1, postazione1);
            Prenotazione prenotazione2 = new Prenotazione(null, LocalDate.of(2024, 12, 23), utente2, postazione2);


            prenotazioneRepository.saveAll(List.of(prenotazione1, prenotazione2));
        };
    }

}