import javafx.collections.ObservableList;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Aula {
    private String nomeAula;
    private String nomeStudente;
    private String orarioEntrata;
    private String strumento;
    private LocalTime orarioSfratto;
    private boolean liberabile = false;
    private String segnaleLiberabile;
    private boolean isOccupata = false;
    private List<Prenotazione> prenotazioni = new ArrayList<>();

    public Aula(String nomeAula) {
        this.nomeAula = nomeAula;
    }

    public String getNomeAula() {
        return nomeAula;
    }

    public void setNomeAula(String nomeAula) {
        this.nomeAula = nomeAula;
    }

    public String getNomeStudente() {
        return nomeStudente;
    }

    public void setNomeStudente(String nomeStudente) {
        this.nomeStudente = nomeStudente;
    }

    public String getOrarioEntrata() {
        return orarioEntrata;
    }

    public void setOrarioEntrata(String orarioEntrata) {
        this.orarioEntrata = orarioEntrata;
    }

    public String getStrumento() {
        return strumento;
    }

    public void setStrumento(String strumento) {
        this.strumento = strumento;
    }

    public LocalTime getOrarioSfratto(){
        if (orarioEntrata == null){
            return null;
        } else{
            //return String.valueOf(Double.valueOf(orarioEntrata) + 2.00);
            LocalTime entrataTime = LocalTime.parse(orarioEntrata, DateTimeFormatter.ISO_LOCAL_TIME);
            orarioSfratto =  entrataTime.plusHours(2);
            return orarioSfratto;
        }
    }
    public void setOrarioSfratto(){
        LocalTime entrataTime = LocalTime.parse(orarioEntrata, DateTimeFormatter.ISO_LOCAL_TIME);
        orarioSfratto = entrataTime.plusHours(2);
        // String.valueOf(Double.valueOf(orarioEntrata) + 2.00);
    }

    public boolean getLiberabile() {
        return liberabile;
    }

    public void setLiberabileOn() {
        liberabile = true;
    }
    public void setLiberabileOff() {
        liberabile = false;
    }
    public String getSegnaleLiberabile(){
        if (liberabile){
            segnaleLiberabile = "LIBERABILE";
        } else {
            segnaleLiberabile = "";
        }
        return segnaleLiberabile;
    }
    public void setSegnaleLiberabile(){
        if (liberabile){
            segnaleLiberabile = "LIBERABILE";
        } else {
            segnaleLiberabile = "";
        }
    }
    public boolean getIsOccupata(){
        return isOccupata;
    }
    public void setIsOccupata() {
        if (nomeStudente != null){
            this.isOccupata = true;
        } else {
            this.isOccupata = false;
        }
    }
    public List<Prenotazione> getPrenotazioni(){
        return prenotazioni;
    }
    public void setPrenotazioneInAula(Prenotazione prenotazione){
        try {
            prenotazioni.add(prenotazione);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }
    }
}
