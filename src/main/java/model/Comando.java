package model;

public class Comando {
    private int id;
    private String comando;
    private String descripcion;
    private String grupo;

    public Comando() {
        
    }
    
    public Comando(int id, String comando, String descripcion) {
        this();
        this.id = id;
        this.comando = comando;
        this.descripcion = descripcion;
    }

    public Comando(String comando, String descripcion) {
        this();
        this.comando = comando;
        this.descripcion = descripcion;
    }
    
    public int getId() {
        return id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
