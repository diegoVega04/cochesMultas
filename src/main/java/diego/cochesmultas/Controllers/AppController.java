package diego.cochesmultas.Controllers;


import diego.cochesmultas.Clases.Coche;
import diego.cochesmultas.DAO.CocheDAOimpl;
import diego.cochesmultas.Util.Validar;
import diego.cochesmultas.Util.Alerta;
import diego.cochesmultas.app;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController {
    @FXML
    public TextField matricula;
    @FXML
    public TextField marca;
    @FXML
    public MenuItem familiar;
    @FXML
    public MenuItem deportivo;
    @FXML
    public MenuItem suv;
    @FXML
    public MenuItem furgoneta;
    @FXML
    public Button btnLimpiar;
    @FXML
    public Button btnNuevo;
    @FXML
    public Button btnActualizar;
    @FXML
    public Button btnEliminar;
    @FXML
    public Button btnMultas;
    @FXML
    public TableColumn clmMatricula;
    @FXML
    public TableColumn clmMarca;
    @FXML
    public TableColumn clmModelo;
    @FXML
    public TableColumn clmTipo;
    @FXML
    public TableView tablaCoches;
    @FXML
    public TextField modelo;
    @FXML
    public MenuButton menuTipo;


    public ObservableList<Coche> coches = FXCollections.observableArrayList();
    private final CocheDAOimpl gest = new CocheDAOimpl();
    private String tipo;
    public static Coche seleccionado;


    public void initialize() {
        clmMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        clmMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        clmModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        clmTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        coches.addAll(gest.cargarCoches());
        //asignar la observable list a la tabla
        tablaCoches.setItems(coches);
    }

    public void limpiar() {
        matricula.setText("");
        marca.setText("");
        modelo.setText("");
        menuTipo.setText("");
        tipo = null;
        matricula.setDisable(false);
        btnNuevo.setDisable(false);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnMultas.setDisable(true);
    }

    @FXML
    public void OnBtnLimpiar(ActionEvent event) {
        limpiar();
    }

    @FXML
    public void OnBtnNuevo(ActionEvent event) {
        Coche a = new Coche(matricula.getText(), marca.getText(), modelo.getText(), tipo);
        if (!coches.contains(a)) {
            if (Validar.matricula(a)) {
                if (Validar.marcaModelo(a.getMarca(), a.getModelo())) {
                    if (gest.guardarCoche(a)) {
                        coches.add(a);
                        limpiar();
                    }
                } else {
                    Alerta.notificacion("Error en formato de la marca y/o modelo");
                }
            } else {
                Alerta.notificacion("Error en el formato de la matricula");
            }
        } else {
            Alerta.notificacion("Ya existe un coche con esa matricula");
        }
    }

    @FXML
    public void OnBtnActualizar(ActionEvent event) {
        System.out.println(matricula.getText());
        if (Validar.marcaModelo(seleccionado.getMarca(), seleccionado.getModelo())) {
            seleccionado.setMarca(marca.getText());
            seleccionado.setModelo(modelo.getText());
            seleccionado.setTipo(tipo);

            if (gest.actualizarCoche(seleccionado)) {
                coches.remove(seleccionado);
                coches.add(seleccionado);
                limpiar();
                Alerta.notificacion("Coche actualizado con exito");
            } else {
                Alerta.notificacion("Error al intentar actualizar el coche");
            }
        } else {
            Alerta.notificacion("Error en el formato de marca y/o modelo");
        }
    }

    @FXML
    public void OnBtnEliminar(ActionEvent event) {
        if (seleccionado != null) {
            if (Alerta.confirmacion("¿Seguro que desea borrar el coche seleccionado?") == 0) {
                if (gest.eliminarCoche(seleccionado)) {
                    coches.remove(seleccionado);
                    limpiar();
                    Alerta.notificacion("Coche borrado con exito");
                } else {
                    Alerta.notificacion("Error al intentar borrar el coche");
                }
            }
        } else {
            Alerta.notificacion("No ha seleccionado ningun coche de la lista");
        }
    }

    @FXML
    public void clicTabla(MouseEvent mouseEvent) {
        seleccionado = (Coche) tablaCoches.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            matricula.setText(seleccionado.getMatricula());
            marca.setText(seleccionado.getMarca());
            modelo.setText(seleccionado.getModelo());
            menuTipo.setText(seleccionado.getTipo());
            tipo = seleccionado.getTipo();
            matricula.setDisable(true);
            btnNuevo.setDisable(true);
            btnActualizar.setDisable(false);
            btnEliminar.setDisable(false);
            btnMultas.setDisable(false);
        }
    }

    @FXML
    public void selFamiliar(ActionEvent event) {
        tipo = "familiar";
        menuTipo.setText("Familiar");
    }

    @FXML
    public void selDeportivo(ActionEvent event) {
        tipo = "deportivo";
        menuTipo.setText("Deportivo");
    }

    @FXML
    public void selSUV(ActionEvent event) {
        tipo = "SUV";
        menuTipo.setText("SUV");
    }

    @FXML
    public void selFurgoneta(ActionEvent event) {
        tipo = "Furgoneta";
        menuTipo.setText("Furgoneta");
    }

    @FXML
    public void OnBtnMultas(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("/Interfaces/multas.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);

        Stage ventana = new Stage();
        ventana.setTitle("Multas");
        ventana.setScene(scene);
        ventana.show();
    }
}