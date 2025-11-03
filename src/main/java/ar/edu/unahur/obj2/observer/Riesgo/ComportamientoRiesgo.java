package ar.edu.unahur.obj2.observer.Riesgo;

import ar.edu.unahur.obj2.observer.Observador.IEntidad;

public abstract class ComportamientoRiesgo {
    public Integer riesgo(IEntidad entidad){
        return doRiesgo(entidad);
    }
    public abstract Integer doRiesgo(IEntidad entidad);
}
