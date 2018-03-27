package pruebas.jpa.model;

import pruebas.jpa.model.asyncrequest.Estados;

import java.util.Map;

public interface AsyncRequestDAO {
    AsyncRequest find(String numeroOrden);

    AsyncRequest add(String casoSFDC, String proceso, String operacion);

    AsyncRequest moveToNextState(String numeroOrden);

    AsyncRequest setEstado(String numeroOrden, Estados estado);

    AsyncRequest setEstado(AsyncRequest a, Estados estado);

    AsyncRequest addExtraData(String numeroOrden, String dato, String valor);

    AsyncRequest addExtraData(AsyncRequest a, String dato, String valor);

    AsyncRequest addExtraData(String numeroOrden, Map<String, String> data);

    AsyncRequest addExtraData(AsyncRequest a, Map<String, String> data);
}
