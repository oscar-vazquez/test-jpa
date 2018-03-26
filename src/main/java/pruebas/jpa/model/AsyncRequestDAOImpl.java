package pruebas.jpa.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pruebas.jpa.model.asyncrequest.Estados;

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
        persist(a);
        return a;
    }

    @Override
    public AsyncRequest moveToNextState(String numeroOrden) {
        AsyncRequest a = find(numeroOrden);
        if (a != null) {
            a.moveToNextEstado();
        }
        else {
            logger.warn("Se intento actualizar AsycnRequest inexistente: {}", numeroOrden);
        }

        return a;
    }

    @Override
    public AsyncRequest setEstado(String numeroOrden, Estados estado) {
        AsyncRequest a = find(numeroOrden);
        if (a != null) {
            a.setEstado(estado);
        }
        else {
            logger.warn("Se intento actualizar AsycnRequest inexistente: {}", numeroOrden);
        }

        return a;
    }
}
