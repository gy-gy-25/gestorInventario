package com.example.demogestorinventario.Modelo;

import javafx.beans.property.*;

public class Producto {
    private final StringProperty codigo = new SimpleStringProperty("this", "codigo", "");
    private final StringProperty nombre = new SimpleStringProperty("this", "nombre", "");
    private final IntegerProperty stock = new SimpleIntegerProperty("this", "stock", 0);
    private final DoubleProperty precio = new SimpleDoubleProperty("this", "precio", 0.0);

    public Producto(String codigo, String nombre, int stock, double precio) {
        setCodigo(codigo);
        setNombre(nombre);
        setStock(stock);
        setPrecio(precio);
    }

    public String getCodigo() {return codigo.get();}
    public StringProperty codigoProperty() {return codigo;}
    public void setCodigo(String codigo) {this.codigo.set(codigo);}

    public String getNombre() {return nombre.get();}
    public StringProperty nombreProperty() {return nombre;}
    public void setNombre(String nombre) {this.nombre.set(nombre);}

    public int getStock() {return stock.get();}
    public IntegerProperty stockProperty() {return stock;}
    public void setStock(int stock) {this.stock.set(stock);}

    public double getPrecio() {return precio.get();}
    public DoubleProperty precioProperty() {return precio;}
    public void setPrecio(double precio) {this.precio.set(precio);}
}
