import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.LocalTimeStringConverter;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EntrataController {
    @FXML private TextField textStudente;
    @FXML private TextField textOraEntrata;
    @FXML private TextField textStrumento;
    Aula aula;

    @FXML
     void initialize() {
        //SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        textStudente.textProperty().addListener((observable, oldValue, newValue) -> aula.setNomeStudente(newValue));
        //textOraEntrata.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), LocalTime.now()));
        textOraEntrata.textProperty().addListener((observable, oldValue, newValue) -> aula.setOrarioEntrata(newValue));
        textStrumento.textProperty().addListener((observable, oldValue, newValue) -> aula.setStrumento(newValue));

    }
    void update() {
        textStudente.textProperty().set(aula.getNomeStudente());
        textOraEntrata.textProperty().set(aula.getOrarioEntrata());
        textStrumento.textProperty().set(aula.getStrumento());
        aula.setIsOccupata();
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
        update();
    }
}
