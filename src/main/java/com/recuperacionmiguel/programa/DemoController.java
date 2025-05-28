package com.recuperacionmiguel.programa;

import es.componente.ControladorComponente;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DemoController extends Application {

  private List<String> seleccion = new ArrayList<>();
  @FXML
  private TextArea outputArea;
  @FXML
  private TextField inputArea;
  @FXML
  private ControladorComponente componente1;

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader AppLoader = new FXMLLoader(DemoController.class.getResource("hello-view.fxml"));
    Parent root = AppLoader.load();
    Scene scene = new Scene(root);
    //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/es/componente/styles.css")).toExternalForm());
    stage.setTitle("Imagina que funciona y todo...");
    stage.setScene(scene);
    stage.show();
  }


  @FXML
  public void handleGet() {
    seleccion = componente1.get();
    outputArea.setText("Elementos:\n" + String.join(", ", seleccion));
  }

  public void handleSet() {
    String texto = inputArea.getText().trim();
    if (!texto.isEmpty()) {
      componente1.set(texto);
      inputArea.clear();
    };
  }

  @FXML
  public void handleReset() {
    componente1.reset();
    outputArea.clear();
  }

  public static void main(String[] args) {
    launch();
  }
}
