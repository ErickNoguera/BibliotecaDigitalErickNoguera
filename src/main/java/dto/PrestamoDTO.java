package dto;

public class PrestamoDTO {
    private int id;
    private int libroId;
    private String tituloLibro;
    private String fechaPrestamo;

    public PrestamoDTO(int id, int libroId, String tituloLibro, String fechaPrestamo) {
        this.id = id;
        this.libroId = libroId;
        this.tituloLibro = tituloLibro;
        this.fechaPrestamo = fechaPrestamo;
    }

    public int getId() {
        return id;
    }
    public int getLibroId() {
        return libroId;
    }
    public String getTituloLibro() {
        return tituloLibro;
    }
    public String getFechaPrestamo() {
        return fechaPrestamo;
    }
}