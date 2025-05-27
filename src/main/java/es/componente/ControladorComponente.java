package es.componente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorComponente extends VBox {

  @FXML
  private TextField textField;
  @FXML
  private Button button;
  @FXML
  private FlowPane flowPane;

  private List<String> seleccionados = new ArrayList<String>();

  static ControladorComponente controlador;

  public ControladorComponente() throws IOException {
    super();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Componente.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
    initialize();
  }

  private void initialize() {
    button.setOnAction(e -> agregarBadge());
    textField.setOnAction(e -> agregarBadge());
  }

  public List<String> getElementosSeleccionados() {
    return new ArrayList<>(seleccionados);
  }

  private void agregarBadge() {
    String texto = textField.getText().trim();
    if (texto.isEmpty() || seleccionados.contains(texto)) {
      textField.clear();
      return;
    }
    seleccionados.add(texto);

    Label lbl = new Label(texto);
    lbl.getStyleClass().add("badge-label");

    Button close = new Button("✕");
    close.getStyleClass().add("badge-close");

    HBox hb = new HBox(lbl, close);  // ✅ Primero declarar el HBox
    hb.getStyleClass().add("badge");
    hb.setSpacing(4);

    close.setOnAction(evt -> {
      flowPane.getChildren().remove(hb);
      seleccionados.remove(texto);
    });

    flowPane.getChildren().add(hb);
    textField.clear();
  }


  public void addBadge(String texto) {
    if (texto == null) return;
    String badgeText = texto.trim();
    if (badgeText.isEmpty() || seleccionados.contains(badgeText)) return;

    seleccionados.add(badgeText);

    Label lbl = new Label(badgeText);
    lbl.getStyleClass().add("badge-label");

    Button close = new Button("✕");
    close.getStyleClass().add("badge-close");

    HBox hb = new HBox(lbl, close);
    hb.getStyleClass().add("badge");
    hb.setSpacing(4);

    close.setOnAction(evt -> {
      flowPane.getChildren().remove(hb);
      seleccionados.remove(badgeText);
    });

    flowPane.getChildren().add(hb);
  }

  public void reset() {
    flowPane.getChildren().clear();
  }
}
