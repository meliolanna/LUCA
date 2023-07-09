import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;

public class PrenotazioneController {
    @FXML
    private TextField textStudente;
    @FXML private TextField textOrarioInizio;
    @FXML private TextField textOrarioFine;
    @FXML private TextField textMotivazione;
    Prenotazione prenotazione;

    @FXML
    void initialize() {
        textStudente.textProperty().addListener((observable, oldValue, newValue) -> prenotazione.setNomePrenotazione(newValue));
        textOrarioInizio.textProperty().addListener((observable, oldValue, newValue) -> prenotazione.setOrarioInizio(LocalTime.parse(newValue)));
        textOrarioFine.textProperty().addListener((observable, oldValue, newValue) -> prenotazione.setOrarioFine(LocalTime.parse(newValue)));
        textMotivazione.textProperty().addListener((observable, oldValue, newValue) -> prenotazione.setMotivazione(newValue));
    }
    void update() {
        textStudente.textProperty().set(prenotazione.getNomePrenotazione());
        textMotivazione.textProperty().set(prenotazione.getMotivazione());
        if (prenotazione.getOrarioInizio() != null){
            textOrarioInizio.textProperty().set(prenotazione.getOrarioInizio().toString());
        }
        if (prenotazione.getOrarioFine() != null){
            textOrarioFine.textProperty().set(prenotazione.getOrarioFine().toString());
        }
    }

    public Prenotazione getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(Prenotazione prenotazione) {
        this.prenotazione = prenotazione;
        update();
    }
}
