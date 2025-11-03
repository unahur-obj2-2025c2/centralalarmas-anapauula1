package ar.edu.unahur.obj2.observer.Riesgo;

import ar.edu.unahur.obj2.observer.Observador.IEntidad;

public class Promedio extends ComportamientoRiesgo{
    @Override
    public Integer doRiesgo(IEntidad entidad) {
        return entidad.promedioDeAlertas();
    }
}
