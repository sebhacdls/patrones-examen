package cl.patrones.examen.productos.model;

public class Producto {

    private String nombre;
    private String imagen;
    private String sku;
    private double precioLista;
    private double precioFinal;

    public Producto() {
    }

    public Producto(String nombre, String imagen, String sku, double precioLista) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.sku = sku;
        this.precioLista = precioLista;
        this.precioFinal = precioLista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(double precioLista) {
        this.precioLista = precioLista;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }
}
