package pe.edu.cibertec.app_demo.bean;

public class PlatosBean {

    private String url,titulo,tiempo,precio;

    public PlatosBean(String url, String titulo, String tiempo, String precio) {
        this.url = url;
        this.titulo = titulo;
        this.tiempo = tiempo;
        this.precio = precio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
