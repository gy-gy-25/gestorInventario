package com.example.demogestorinventario;

import com.example.demogestorinventario.Modelo.Producto;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    ObservableList<Producto> p = FXCollections.observableArrayList();
    @FXML
    private Label lblSeleccionado;
    @FXML
    private TableColumn<Producto, String> tcNombre;
    @FXML
    private TextField tfPrecio;
    @FXML
    private Button btnNuevo;
    @FXML
    private TextField tfCodigo;
    @FXML
    private TableColumn<Producto, Integer> tcStock;
    @FXML
    private TextField tfStock;
    @FXML
    private Slider sliderTitulo;
    @FXML
    private Label lblNotaEspejo;
    @FXML
    private TextArea taNotaRapida;
    @FXML
    private Label lblTamanioTitulo;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField tfNombre;
    @FXML
    private TableColumn<Producto, String> tcCodigo;
    @FXML
    private Button btnAnadir;
    @FXML
    private TableView<Producto> tbProductos;
    @FXML
    private Label lblPX;
    @FXML
    private TextField tfNotaEspejo;
    @FXML
    private TableColumn<Producto, Double> tcPrecio;

    @FXML
    private void initialize() {
        tcCodigo.setCellValueFactory(cellData -> cellData.getValue().codigoProperty());
        tcNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        tcStock.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        tcPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());

        tbProductos.setItems(p);

        // Configurar el evento del botón "Añadir"
        btnAnadir.setOnAction(event -> {
            String codigo = tfCodigo.getText();
            String nombre = tfNombre.getText();
            int stock = Integer.parseInt(tfStock.getText());
            double precio = Double.parseDouble(tfPrecio.getText());

            Producto producto = new Producto(codigo, nombre, stock, precio);
            p.add(producto);
        });

        // Configurar el evento del botón "Eliminar"
        btnEliminar.setOnAction(event -> {
            // Obtener el producto seleccionado
            Producto p = tbProductos.getSelectionModel().getSelectedItem();
            if (p != null) {
                tbProductos.getItems().remove(p);
            } else  {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, selecciona un producto para eliminar.");
                alert.show();
            }
        });

        BooleanBinding camposAltaBien = Bindings.createBooleanBinding(() -> {
            Boolean camposTextoMal = tfCodigo.getText().isEmpty() || tfNombre.getText().isEmpty();
            Boolean stockMal = false;
            Boolean precioMal = false;

            try {
                Integer.parseInt(tfStock.getText().trim());
                Double.parseDouble(tfPrecio.getText().trim());
            } catch (NumberFormatException e) {
                stockMal = true;
                precioMal = true;
            }
            return !camposTextoMal && !stockMal && !precioMal;
        }, tfCodigo.textProperty(), tfNombre.textProperty(), tfStock.textProperty(), tfPrecio.textProperty());

        btnAnadir.disableProperty().bind(camposAltaBien.not());
        btnEliminar.disableProperty().bind(tbProductos.getSelectionModel().selectedItemProperty().isNull());

        btnNuevo.setOnAction(event -> {
            tfCodigo.clear();
            tfNombre.clear();
            tfStock.clear();
            tfPrecio.clear();
            taNotaRapida.clear();
            lblSeleccionado.setText("Seleccionado: Ninguno");
        });

        tbProductos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                lblSeleccionado.setText("Seleccionado: " + newValue.getNombre());
            } else {
                lblSeleccionado.setText("Seleccionado: Ninguno");
            }
        });
    }
}
