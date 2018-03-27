package pruebas.jpa.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pruebas.jpa.model.asyncrequest.Estados;

import java.util.Map;

public class AsyncRequestDAOImpl extends AbstractDAO<AsyncRequest> implements AsyncRequestDAO {
    private static final Logger logger = LoggerFactory.getLogger(AsyncRequestDAOImpl.class);

    public AsyncRequestDAOImpl() {
        super(AsyncRequest.class);
    }

    @Override
    public AsyncRequest find(String numeroOrden) {
        return super.find(numeroOrden);
    }

    @Override
    public AsyncRequest add(String casoSFDC, String proceso, String operacion) {
        AsyncRequest a = new AsyncRequest(casoSFDC, proceso, operacion);
        AsyncRequestLog log = new AsyncRequestLog(a, "Aceptado", "Request recibido y aceptado para procesar");
        a.getLog().add(log);
        persist(a);
        return a;
    }

    @Override
    public AsyncRequest moveToNextState(String numeroOrden) {
        AsyncRequest a = getForUpdate(numeroOrden);
        if (a != null) {
            setEstado(a, a.getEstado().next());
        }
        return a;
    }

    @Override
    public AsyncRequest setEstado(String numeroOrden, Estados estado) {
        AsyncRequest a = getForUpdate(numeroOrden);
        if (a != null) {
            setEstado(a, estado);
        }
        return a;
    }

    @Override
    public AsyncRequest setEstado(AsyncRequest a, Estados estado) {
        String estadoActual = a.getEstado().toString();
        String strEstado = estado.toString();
        a.setEstado(estado);
        AsyncRequestLog log = new AsyncRequestLog(a, "Cambio Estado", String.format("Cambio estado de \"%s\" a \"%s\"", estadoActual, strEstado));
        a.getLog().add(log);
        return a;
    }

    @Override
    public AsyncRequest addExtraData(String numeroOrden, String dato, String valor) {
        AsyncRequest a = getForUpdate(numeroOrden);
        if (a != null) {
            addExtraData(a, dato, valor);
        }
        return a;
    }

    @Override
    public AsyncRequest addExtraData(AsyncRequest a, String dato, String valor) {
        if (a != null) {
            AsyncRequestXtra d = new AsyncRequestXtra(a, dato, valor);
            a.getDatosExtra().add(d);
            AsyncRequestLog log = new AsyncRequestLog(a, "Agregado dato extra", String.format("Dato = \"%s\", valor = \"%s\"", dato, valor));
            a.getLog().add(log);
        }
        return a;
    }

    @Override
    public AsyncRequest addExtraData(String numeroOrden, Map<String, String> data) {
        AsyncRequest a = getForUpdate(numeroOrden);
        if (a != null) {
            addExtraData(a, data);
        }
        return a;
    }

    @Override
    public AsyncRequest addExtraData(AsyncRequest a, Map<String, String> data) {
        if (a != null) {
            for (Map.Entry<String, String> d: data.entrySet()) {
                addExtraData(a, d.getKey(), d.getValue());
            }
        }
        return a;
    }

    private AsyncRequest getForUpdate(String numeroOrden) {
        AsyncRequest a = find(numeroOrden);
        if (a == null) {
            logger.warn("Se intento actualizar AsycnRequest inexistente: {}", numeroOrden);
        }
        return a;
    }
}
