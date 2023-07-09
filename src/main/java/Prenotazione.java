import java.time.LocalTime;

public class Prenotazione {
    String nomePrenotazione;
    LocalTime orarioInizio;
    LocalTime orarioFine;
    String motivazione;

    public Prenotazione(){
    }
    public Prenotazione(Prenotazione prenotazione){
        this.nomePrenotazione = prenotazione.getNomePrenotazione();
        this.orarioInizio = prenotazione.getOrarioInizio();
        this.orarioFine = prenotazione.getOrarioFine();
        this.motivazione = prenotazione.getMotivazione();
    }
    public Prenotazione(String nomePrenotazione, LocalTime orarioInizio){
        this.nomePrenotazione = nomePrenotazione;
        this.orarioInizio = orarioInizio;
    }
    public Prenotazione(String nomePrenotazione, LocalTime orarioInizio, LocalTime orarioFine){
        this.nomePrenotazione = nomePrenotazione;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
    }

    public String getNomePrenotazione() {
        return nomePrenotazione;
    }
    public void setNomePrenotazione(String nomePrenotazione) {
        this.nomePrenotazione = nomePrenotazione;
    }

    public LocalTime getOrarioInizio() {
        return orarioInizio;
    }
    public void setOrarioInizio(LocalTime orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public LocalTime getOrarioFine() {
        return orarioFine;
    }
    public void setOrarioFine(LocalTime orarioFine) {
        this.orarioFine = orarioFine;
    }

    public String getMotivazione() {
        return motivazione;
    }
    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public String toString() {
        return "Prenotazione di " + nomePrenotazione + ", dalle " + orarioInizio.toString() + " alle " + orarioFine.toString()
                + " per il motivo: " + motivazione;
    }
}
