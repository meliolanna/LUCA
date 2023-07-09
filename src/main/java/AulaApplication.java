import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AulaApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mappaAule.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("LUCA - Registro Aule");
        primaryStage.setScene(scene);
        Image icon = new Image("note.png");
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }

}
