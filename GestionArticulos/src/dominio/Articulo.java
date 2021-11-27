package dominio;

import java.util.Date;

public class Articulo {
    
    private int idArticulo;
    private String nombre;
    private String descripcion;
    private double precio;
    private Date fecha;
    //private TipoCategoria categoria;
    
    private static int contadorArticulos = 1;

    public Articulo() {
        this.idArticulo = Articulo.contadorArticulos++;
    }

    public Articulo(String nombre, String descripcion, double precio, Date fecha) {
        this();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
    }

    public Articulo(int idArticulo, String nombre, String descripcion, double precio, Date fecha) {
        this.idArticulo = idArticulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
    }
    
    

    public int getIdArticulo() {
        return idArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public static int getContadorArticulos() {
        return contadorArticulos;
    }

    public static void setContadorArticulos(int contadorArticulos) {
        Articulo.contadorArticulos = contadorArticulos;
    }

    @Override
    public String toString() {
        return "Articulo{" + "idArticulo=" + idArticulo + ", nombre=" + nombre + 
                ", descripcion=" + descripcion + ", precio=" + precio + 
                ", fecha=" + fecha + '}';
    }
    
    
    
    
    
}
