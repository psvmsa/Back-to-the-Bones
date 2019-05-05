package fxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage mainWindow) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        mainWindow.setTitle("Back to the Bones");
        mainWindow.setScene(new Scene(root, 320, 240));
        mainWindow.setResizable(false);
        mainWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
