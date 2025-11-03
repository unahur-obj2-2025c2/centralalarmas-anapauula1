package ar.edu.unahur.obj2.observer;

public class Alerta {
    private String tipo;
    private Integer nivel;
    public Alerta(String tipo, Integer nivel) {
        this.tipo = tipo;
        this.nivel = nivel;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Integer getNivel() {
        return nivel;
    }
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
    public Boolean esCritica(){
        return this.nivel >=8;
    }
}   
