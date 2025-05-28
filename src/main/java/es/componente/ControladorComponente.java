package es.componente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Máximo Bautista Lugo mblugo01@iesalbarregas.es
 * @version 1.0
 * @since 2025-05-28
 * <p>
 * Controlador de un componente JavaFX que permite añadir etiquetas (badges)
 * dinámicamente a un flujo (FlowPane) a partir de un campo de texto.
 * <p>
 * Cada badge consta de un {@link Label} con el texto y un botón de cierre
 * para eliminarlo.
 * </p>
 */
public class ControladorComponente extends VBox {

  @FXML
  private TextField textField;

  @FXML
  private Button button;

  @FXML
  private FlowPane flowPane;

  /** Lista interna con los textos actualmente seleccionados (badges). */
  private final List<String> seleccionados = new ArrayList<>();

  /**
   * Crea una nueva instancia del controlador y carga el FXML asociado.
   *
   * @throws IOException si hay un error al cargar el archivo FXML.
   */
  public ControladorComponente() throws IOException {
    super();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Componente.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
    initialize();
  }

  /**
   * Inicializa los manejadores de eventos para el botón y el campo de texto.
   * Invocado internamente tras la carga del FXML.
   */
  private void initialize() {
    button.setOnAction(e -> setComponente());
    textField.setOnAction(e -> setComponente());
  }

  /**
   * Obtiene la lista de textos de los badges actualmente añadidos.
   *
   * @return lista inmutable de strings seleccionados.
   */
  public List<String> get() {
    return new ArrayList<>(seleccionados);
  }

  /**
   * Añade un nuevo badge con el texto proporcionado, si no está vacío ni duplicado.
   * <p>
   * Este método puede usarse desde código externo para insertar badges
   * sin necesidad de interacción directa con la UI.
   * </p>
   *
   * @param texto el texto del badge a añadir; si es {@code null}, vacío o ya existe,
   *              no se hace nada.
   */
  public void set(String texto) {
    if (texto == null) {
      return;
    }
    String badgeText = texto.trim();
    if (badgeText.isEmpty() || seleccionados.contains(badgeText)) {
      return;
    }

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

  /**
   * Elimina todos los badges existentes y limpia la selección interna.
   */
  public void reset() {
    seleccionados.clear();
    flowPane.getChildren().clear();
  }

  /**
   * Manejador privado vinculado al botón y al TextField para crear y añadir
   * el badge correspondiente al texto ingresado por el usuario.
   */
  private void setComponente() {
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

    HBox hb = new HBox(lbl, close);
    hb.getStyleClass().add("badge");
    hb.setSpacing(4);

    close.setOnAction(evt -> {
      flowPane.getChildren().remove(hb);
      seleccionados.remove(texto);
    });

    flowPane.getChildren().add(hb);
    textField.clear();
  }
}
