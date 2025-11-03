package ar.edu.unahur.obj2.observer.Riesgo;

import ar.edu.unahur.obj2.observer.Observador.IEntidad;

public class Critico extends ComportamientoRiesgo {
    @Override
    public Integer doRiesgo(IEntidad entidad) {
        if(entidad.ultimaAlerta().esCritica()){
            return 10;
        }
        else{
            return entidad.ultimaAlerta().getNivel();
        }
    }
}
