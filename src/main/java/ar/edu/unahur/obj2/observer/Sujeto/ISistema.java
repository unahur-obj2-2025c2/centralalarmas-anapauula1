package ar.edu.unahur.obj2.observer.Sujeto;


import ar.edu.unahur.obj2.observer.Observador.IEntidad;

public interface ISistema {
    void registrarEntidad(IEntidad unaEntidad);
    void eliminarEntidad(IEntidad unaEntidad);
    void emitirAlerta(String Tipo,Integer nivel);
}
