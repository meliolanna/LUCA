import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalTime;
import java.util.List;

public class PrenotazioniController {
    @FXML private TableView<Prenotazione> elencoPrenotazioni;
    @FXML private TableColumn<Prenotazione, String> nomePrenotazione;
    @FXML private TableColumn<Prenotazione, LocalTime> inizioPrenotazione;
    @FXML private TableColumn<Prenotazione, LocalTime> finePrenotazione;
    @FXML private TableColumn<Prenotazione, String> motivoPrenotazione;
    MappaAuleController mappaAuleController;
    //private List<Prenotazione> elencoPren = mappaAuleController.getAulaS().get(mappaAuleController.selectedIndex()).getPrenotazioni();

    @FXML
    void initialize() {
        nomePrenotazione.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>("nomePrenotazione"));
        //inizioPrenotazione.setCellValueFactory(new PropertyValueFactory<Prenotazione, LocalTime>("orarioInizio"));
        //finePrenotazione.setCellValueFactory(new PropertyValueFactory<Prenotazione, LocalTime>("orarioFine"));
        motivoPrenotazione.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>("motivazione"));
        elencoPrenotazioni.setItems(getPrenotazioni());
        //pianoSuperiore.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAulaDetails(newValue));

    }

    ObservableList<Prenotazione> getPrenotazioni() {
        ObservableList<Prenotazione> elencoPrenObv = FXCollections.observableArrayList(mappaAuleController.getPrenotazioniAule());
        //prenotazioni.addAll(elencoPren);
        return elencoPrenObv;
    }

}
