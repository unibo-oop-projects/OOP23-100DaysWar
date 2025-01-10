# Obiettivo del Gruppo

Il gruppo si pone di realizzare una versione semplificata del gioco [Conq.io](https://www.conq.io/).

All'avvio dell'applicazione, l'utente sarà invitato a inserire uno username. Successivamente, attraverso un menù interattivo, sarà possibile scegliere tra il caricamento dell'ultimo salvataggio o l'inizio di una nuova partita.  
Ogni partita si svolge su una mappa generata proceduralmente, con celle di spawn assegnate rispettivamente al giocatore e al bot avversario. Il gioco si articola in un ciclo temporale di 100 giorni virtuali, in cui ogni giorno viene simulato in un intervallo di qualche secondo. Il giocatore non è obbligato a effettuare una mossa ogni giorno, ma trascorsi 4 giorni consecutivi il turno verrà automaticamente ceduto all'avversario.

Durante ogni turno, il giocatore accumula risorse sotto forma di monete, utilizzabili per posizionare o potenziare unità difensive (torri) o offensive (soldati). Le azioni disponibili includono la costruzione di torri difensive o il movimento di soldati già piazzati sulla mappa. I soldati possono essere potenziati fino a un livello massimo pari a 3, mentre le torri difensive possono raggiungere un livello massimo pari a 4. Se un soldato viene mosso in una cella occupata da un'unità nemica, inizia un combattimento risolto attraverso un sistema di lancio di dadi, proporzionale al livello delle unità coinvolte. La vittoria viene assegnata al giocatore che ottiene il punteggio totale più alto con i propri dadi.

La partita può concludersi prima dei 100 giorni se un giocatore riesce a conquistare la cella di spawn dell'avversario. In caso contrario, al termine del periodo, la vittoria verrà assegnata al giocatore che controlla il maggior numero di celle.

## Funzionalità Minime Ritenute Obbligatorie

- Implementazione logica e grafica del menù iniziale di gioco.
- Implementazione logica e grafica del pannello di gioco composto da una mappa su cui si svolge la partita e menù di posizionamento, potenziamento e movimento dei soldati.
- Implementazione di un manuale che illustra le regole dettagliate di gioco e le varie componenti.
- Gestione dei turni di gioco tra player e bot.
- Gestione del movimento dei soldati sulla mappa.
- Gestione della difesa offerta dalle torri.
- Gestione del combattimento tra due soldati.
- Gestione delle statistiche di gioco in tempo reale.
- Gestione del termine della partita.
- Gestione del salvataggio e del caricamento dell’ultima partita in corso.

## Funzionalità Ritenute Opzionali

- Aggiunta di diverse tipologie di torri difensive con relativa modifica del menù.
- Miglioramento strategia del bot avversario.
- Implementazione logica e grafica di caselle bonus.

## Challenge Principali

- Corretto utilizzo del pattern architetturale **MVC (Model-View-Controller)**, con relativa gestione delle dipendenze fra Model, View e Controller e buon design.
- Corretto utilizzo del software di controllo versione **Git**, come strumento per la collaborazione.
- Adeguata suddivisione del lavoro all’interno del gruppo.
- Controllo velocità di scorrimento dei giorni.

## Suddivisione del Lavoro

### Bartolini

- Implementazione e visualizzazione del pannello iniziale.
- Implementazione e visualizzazione della mappa di gioco.
- Implementazione e visualizzazione delle torri difensive e delle loro interazioni.

### Balzani

- Implementazione del sistema di monete, del relativo menù e della visualizzazione nel pannello di gioco principale.
- Implementazione e visualizzazione dei dadi.
- Gestione del salvataggio e del caricamento della partita.

### Fabbri

- Implementazione e visualizzazione dei soldati e delle loro interazioni.
- Implementazione del bot avversario.
- Implementazione e visualizzazione delle statistiche di gioco in tempo reale.

### Francalanci

- Implementazione e visualizzazione del combattimento fra soldati.
- Implementazione e visualizzazione dello scorrimento dei turni di gioco.
- Implementazione logica e grafica del manuale di gioco.
- Gestione del termine della partita.