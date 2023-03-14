package com.conversor.convesor_jevs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Main extends Application {
    double x, y = 0;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scc = new Scene(fxmlLoader.load());
        //Ocultar la barra con la opcion cerrar-minimizar de windows
        stage.initStyle(StageStyle.UNDECORATED);
        //Mover la ventana presionando con el mouse
        scc.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        scc.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        //Crear ventana
        stage.setScene(scc);
        //el archivo css y los iconos deben estar dentro de resources
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}