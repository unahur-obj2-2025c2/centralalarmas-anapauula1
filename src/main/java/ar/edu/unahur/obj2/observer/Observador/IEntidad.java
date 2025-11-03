package ar.edu.unahur.obj2.observer.Observador;

import ar.edu.unahur.obj2.observer.Alerta;

public interface IEntidad {
    String nombre();
    void recibeAlerta(Alerta unaAlerta);
    Alerta ultimaAlerta();
    Integer riesgo();
    Integer promedioDeAlertas();
    Integer nivelesDeAlertasCriticas();
}
