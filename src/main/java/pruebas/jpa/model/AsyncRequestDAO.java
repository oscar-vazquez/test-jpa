package pruebas.jpa.model;

import pruebas.jpa.model.asyncrequest.Estados;

public interface AsyncRequestDAO {
    AsyncRequest find(String numeroOrden);

    AsyncRequest add(String casoSFDC, String proceso, String operacion);

    AsyncRequest moveToNextState(String numeroOrden);

    AsyncRequest setEstado(String numeroOrden, Estados estado);
}
