module com.recuperacionmiguel.programa {
  requires javafx.controls;
  requires javafx.fxml;

  opens com.recuperacionmiguel.programa to javafx.fxml;
  opens es.componente to javafx.fxml;
  exports com.recuperacionmiguel.programa;
}