package pruebas.jpa.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class ClienteMappingDAOImpl implements ClienteMappingDAO {
    private static final Logger logger = LoggerFactory.getLogger(ClienteMappingDAOImpl.class);

    private EntityManager entityManager;

    @Override
    public ClienteMapping find(int numeroSuministro) {
        logger.debug("Buscando mapeo suministro {}", numeroSuministro);

        ClienteMapping cm = entityManager.find(ClienteMapping.class, numeroSuministro);

        logger.debug("Se encontro: {}", cm);
        return cm;
    }

    @SuppressWarnings("unused")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
