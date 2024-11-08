package diego.cochesmultas.Controllers;

import diego.cochesmultas.Clases.Coche;
import diego.cochesmultas.Clases.Multa;
import diego.cochesmultas.DAO.MultaDAOimpl;
import diego.cochesmultas.Util.Alerta;
import diego.cochesmultas.Util.Validar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class MultasController {
    @FXML
    public Label txtTitulo;
    @FXML
    public TextField idMulta;
    @FXML
    public TextField precio;
    @FXML
    public DatePicker fecha;
    @FXML
    public Button btnNueva;
    @FXML
    public Button btnEliminar;
    @FXML
    public Button btnActualizar;
    @FXML
    public Button btnLimpiar;
    @FXML
    public TableView tablaMultas;
    @FXML
    public TableColumn clmIdMulta;
    @FXML
    public TableColumn clmPrecio;
    @FXML
    public TableColumn clmFecha;

    private Coche seleccionado;
    private ObservableList<Multa> multas = FXCollections.observableArrayList();
    private MultaDAOimpl gest = new MultaDAOimpl();
    private Multa seleccionada = null;

    public void initialize () {
        this.seleccionado = AppController.seleccionado;
        txtTitulo.setText("Multas: " + seleccionado.getMatricula());
        clmFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        clmPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        clmIdMulta.setCellValueFactory(new PropertyValueFactory<>("id_multa"));

        multas.addAll(gest.cargarMultas(seleccionado.getMatricula()));
        tablaMultas.setItems(multas);
    }

    public void limpiar () {
        btnLimpiar.setDisable(true);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnNueva.setDisable(false);
        idMulta.setText("");
        precio.setText("");
        fecha.setValue(null);
    }

    @FXML
    public void OnBtnNueva(ActionEvent event) {
        if (Validar.precio(precio.getText()) && fecha.getValue() != null) {
            Multa a = new Multa(Double.parseDouble(precio.getText()), fecha.getValue(), seleccionado);
            if (gest.nuevaMulta(a)) {
                multas.add(a);
                limpiar();
            } else {
                Alerta.notificacion("Error al añadir la multa");}
        } else {
            Alerta.notificacion("Error en los campos, reviselos");}
    }

    @FXML
    public void OnBtnEliminar(ActionEvent event) {
        if (seleccionada != null) {
            if (Alerta.confirmacion("¿Seguro que desea borrar esta multa?") == 0) {
                if (gest.eliminarMulta(seleccionada)) {
                    multas.remove(seleccionada);
                    limpiar();
                } else {
                    Alerta.notificacion("Error al intentar eliminar la multa");}
            }
        }
    }

    @FXML
    public void OnBtnActualizar(ActionEvent event) {
        if (seleccionada != null) {
            if (Validar.precio(precio.getText()) && fecha.getValue() != null) {
                Multa a = new Multa(Double.parseDouble(precio.getText()), fecha.getValue(), seleccionado);
                if (gest.actualizarMulta(a)) {
                    seleccionada.setFecha(a.getFecha());
                    seleccionada.setPrecio(a.getPrecio());
                    limpiar();
                } else {
                    Alerta.notificacion("Error al actualizar la multa");}
            } else {
                Alerta.notificacion("Error en los campos, reviselos");}
        }
    }

    @FXML
    public void OnBtnLimpiar(ActionEvent event) {
        limpiar();
    }

    @FXML
    public void clicTablaMultas(MouseEvent mouseEvent) {
        btnLimpiar.setDisable(false);
        btnActualizar.setDisable(false);
        btnEliminar.setDisable(false);
        btnNueva.setDisable(true);
        seleccionada = (Multa) tablaMultas.getSelectionModel().getSelectedItem();

        idMulta.setText(Long.toString(seleccionada.getId_multa()));
        precio.setText(Double.toString(seleccionada.getPrecio()));
        fecha.setValue(seleccionada.getFecha());
    }
}
