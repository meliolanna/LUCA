import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalTime;
import java.util.*;


public class MappaAuleController  {
    //table
    @FXML private TableView<Aula> pianoSuperiore;
    @FXML private TableColumn<Aula, String> aulePianoSuperiore;
    @FXML private TableColumn<Aula, String> nomiPianoSuperiore;
    @FXML private TableColumn<Aula, String> liberabile;

    //labels
    @FXML private Label labelStudente;
    @FXML private Label labelStrumento;
    @FXML private Label labelOraEntrata;
    @FXML private Label labelOraLiberabile;
    @FXML private Label labelStato;
    @FXML private Label labelPrenotazioni;

    @FXML
     void initialize() {
        Timer timer = new Timer(true);
        myTimerTask task = new myTimerTask();
        timer.scheduleAtFixedRate(task, 1000, 1000);

        aulePianoSuperiore.setCellValueFactory(new PropertyValueFactory<Aula, String>("nomeAula"));
        nomiPianoSuperiore.setCellValueFactory(new PropertyValueFactory<Aula, String>("nomeStudente"));
        liberabile.setCellValueFactory(new PropertyValueFactory<Aula, String>("segnaleLiberabile"));
        pianoSuperiore.setItems(getAulaS());

        showAulaDetails(null);
        pianoSuperiore.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAulaDetails(newValue));

    }
    ObservableList<Aula> getAulaS() {
        ObservableList<Aula> auleSup = FXCollections.observableArrayList(
                new Aula("Auditorium"), new Aula("Aula 1"), new Aula("Aula 2"),
                new Aula("Aula 3"), new Aula("Aula 4"), new Aula("Aula 5"),
                new Aula("Aula 6"), new Aula("Aula 8"), new Aula("Aula 9"),
                new Aula("Aula 10"), new Aula("Aula 11"), new Aula("Aula 12"),
                new Aula("Aula 13"), new Aula("Aula 14"), new Aula("Aula 15"),
                new Aula("Aula 16"), new Aula("Aula 17"), new Aula("Aula 18"),
                new Aula("Aula 19"), new Aula("Aula 20"), new Aula("Aula 21"),
                new Aula("Stalloni"), new Aula("Morris"), new Aula("Carrozze"),
                new Aula("Aula 26"), new Aula("Aula 27"), new Aula("Aula 28"),
                new Aula("Aula 29"), new Aula("Aula 30"), new Aula("Aula 31"),
                new Aula("Aula 32"), new Aula("Aula 33"), new Aula("Aula 34"),
                new Aula("Aula 1M"), new Aula("Aula 2M"), new Aula("Aula 3M"),
                new Aula("Aula 4M"), new Aula("Aula 5M"));
        return auleSup;
    }

    public class myTimerTask extends TimerTask{
        @Override
        public void run() {
            for (Aula a : pianoSuperiore.getItems()) {
                if(a.getIsOccupata()){
                    if (a.getOrarioSfratto().isBefore(LocalTime.now())) {
                        a.setLiberabileOn();
                    }
                a.setSegnaleLiberabile();
                }
            }
            pianoSuperiore.refresh();
        }
    }

    private String statoAula(Aula aula) {
        if (aula.getIsOccupata() && !aula.getLiberabile()){
            return "Occupata";
        } else if(aula.getIsOccupata() && aula.getLiberabile()){
            return "LIBERABILE";
        } else {
            return "Libera";
        }
    }
    private void showAulaDetails(Aula aula) {
        if (aula != null ) {
            labelStudente.setText(aula.getNomeStudente());
            labelStrumento.setText(aula.getStrumento());
            labelOraEntrata.setText(aula.getOrarioEntrata());
            if (aula.getOrarioSfratto()!= null){
                labelOraLiberabile.setText(aula.getOrarioSfratto().toString());
            } else {
                labelOraLiberabile.setText("");
            }
            labelStato.setText(statoAula(aula));
            labelPrenotazioni.setText(String.valueOf(aula.getPrenotazioni().toArray().length));
        } else {
            labelStudente.setText("");
            labelStrumento.setText("");
            labelOraEntrata.setText("");
            labelOraLiberabile.setText("");
            labelStato.setText("");
            labelPrenotazioni.setText("");
        }
    }
    int selectedIndex() {
        int selectedIndex = pianoSuperiore.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            throw new NoSuchElementException();
        }
        return selectedIndex;
    }
    public List<Prenotazione> getPrenotazioniAule(){
        List<Prenotazione> nuovaLista = new ArrayList<>(pianoSuperiore.getSelectionModel().getSelectedItem().getPrenotazioni());
        return nuovaLista;
    }
    void showNoPersonSelectedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attenzione!");
        alert.setHeaderText("Non hai selezionato alcuna aula.");
        alert.setContentText("Seleziona un'aula dall'elenco per continuare");
        alert.showAndWait();
    }

    // buttons' actions
    public void handleEntrata(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("entrata.fxml"));
            DialogPane entrataDialogPane = loader.load();
            EntrataController entrataController = loader.getController();

            int selectedIndex =  selectedIndex();
            entrataController.setAula(pianoSuperiore.getItems().get(selectedIndex));

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(entrataDialogPane);
            dialog.setTitle("Inserisci entrata");
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.setOnCloseRequest((dialogEvent)->{
                if (dialog.getResult().getButtonData().isCancelButton()) {//clickedButton.get() == ButtonType.OK) {
                    //non cambia nulla
                } else {
                    try {
                        LocalTime.parse(entrataController.getAula().getOrarioEntrata());
                        pianoSuperiore.getItems().set(selectedIndex, entrataController.getAula());
                        pianoSuperiore.getItems().get(selectedIndex).setIsOccupata();
                        showAulaDetails(pianoSuperiore.getItems().get(selectedIndex));
                        dialog.close();
                    } catch (Exception e) {
                        Alert a = new Alert(Alert.AlertType.WARNING);
                        a.setTitle("Attenzione");
                        a.setHeaderText("Formato orario errato!");
                        a.setContentText("Inserire l'orario nel formato HH:mm.");
                        a.show();
                        dialogEvent.consume();
                    }
                }
            });
            //Optional<ButtonType> clickedButton = dialog.showAndWait();
            dialog.showAndWait();

        } catch (NoSuchElementException e) {
            showNoPersonSelectedAlert();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleVisualizzaPrenotazioni(ActionEvent event) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("prenotazioni.fxml"));
            DialogPane visualizzaPrenDialogPane = loader.load();
            //PrenotazioniController visualizzaPrenController = loader.getController();

            int selectedIndex =  selectedIndex();
            //visualizzaPrenController.setPrenotazione(new Prenotazione());

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(visualizzaPrenDialogPane);
            dialog.setTitle("Elenco Prenotazioni "+ pianoSuperiore.getItems().get(selectedIndex()).getNomeAula());
            dialog.initModality(Modality.WINDOW_MODAL);

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if (clickedButton.get() == ButtonType.OK) {
                //pianoSuperiore.getItems().get(selectedIndex).setPrenotazioneInAula(prenotazioneController.getPrenotazione());
                //System.out.println(pianoSuperiore.getItems().get(selectedIndex).getPrenotazioni());
                //System.out.println(prenotazioneController.getPrenotazione());
            }
        } catch (NoSuchElementException e) {
            showNoPersonSelectedAlert();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void handleUscita(ActionEvent event) throws IOException {
        try {
            int selectedIndex =  selectedIndex();

            Alert alertUscitaAula = new Alert(Alert.AlertType.CONFIRMATION);
            alertUscitaAula.setTitle("Uscita Studente");
            alertUscitaAula.setHeaderText(pianoSuperiore.getItems().get(selectedIndex).getNomeAula().toString());
            alertUscitaAula.setContentText("Confermare l'uscita da questa aula?");

            if(alertUscitaAula.showAndWait().get() == ButtonType.OK) {
                pianoSuperiore.getItems().get(selectedIndex).setNomeStudente(null);
                pianoSuperiore.getItems().get(selectedIndex).setOrarioEntrata(null);
                pianoSuperiore.getItems().get(selectedIndex).setStrumento(null);
                pianoSuperiore.getItems().get(selectedIndex).setOrarioSfratto();
                pianoSuperiore.getItems().get(selectedIndex).setLiberabileOff();
                pianoSuperiore.getItems().get(selectedIndex).setSegnaleLiberabile();
                pianoSuperiore.getItems().get(selectedIndex).setIsOccupata();
            }

        } catch (NoSuchElementException e) {
            showNoPersonSelectedAlert();
        } finally {
            pianoSuperiore.refresh();
            showAulaDetails(null);
        }

    }
    public void handlePrenotazione(ActionEvent event) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("prenotazione.fxml"));
            DialogPane prenotazioneDialogPane = loader.load();
            PrenotazioneController prenotazioneController = loader.getController();

            int selectedIndex =  selectedIndex();
            prenotazioneController.setPrenotazione(new Prenotazione());

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(prenotazioneDialogPane);
            dialog.setTitle("Inserisci prenotazione");
            dialog.initModality(Modality.WINDOW_MODAL);

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if (clickedButton.get() == ButtonType.OK) {
                pianoSuperiore.getItems().get(selectedIndex).setPrenotazioneInAula(prenotazioneController.getPrenotazione());
                System.out.println(pianoSuperiore.getItems().get(selectedIndex).getPrenotazioni());
                System.out.println(prenotazioneController.getPrenotazione());
            }
        } catch (NoSuchElementException e) {
            showNoPersonSelectedAlert();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            showAulaDetails(pianoSuperiore.getItems().get(selectedIndex()));
        }
    }
}
