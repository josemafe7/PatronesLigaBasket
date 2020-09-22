package baloncesto.Estado;

public interface Estado{
    //METODOS INTERFAZ ESTADO
    public boolean fichable();
    public void setPrecioFichaje(double precio);
    public double getPrecioFichaje();
    public String getTipo();
    public void setTipo(String tipo);
}
