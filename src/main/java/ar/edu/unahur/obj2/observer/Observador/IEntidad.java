package ar.edu.unahur.obj2.observer.Observador;

import java.util.List;

import ar.edu.unahur.obj2.observer.Alerta;
import ar.edu.unahur.obj2.observer.Riesgo.ComportamientoRiesgo;

public interface IEntidad {
    String nombre();
    void recibeAlerta(Alerta unaAlerta);
    Alerta ultimaAlerta();
    Integer riesgo();
    Integer promedioDeAlertas();
    Integer nivelesDeAlertasCriticas();
    List<Alerta> getAlertasRecibidas();
    void setComportamiento(ComportamientoRiesgo comportamiento);
}
