package pruebas.jpa.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Collections;

public class MotivoSubmotivoDAOImpl implements MotivoSubmotivoDAO {
    private static final Logger logger = LoggerFactory.getLogger(MotivoSubmotivoDAOImpl.class);

    private EntityManager entityManager;

    @Override
    public MotivoSubmotivo find(String motivo, String submotivo, String sistema) {
        logger.debug("Buscado motivo {}, submotivo {}", motivo, submotivo);
        return entityManager.find(MotivoSubmotivo.class, new MotivoSubmotivoPK(motivo, submotivo, MotivoSubmotivo.Sistema.get(sistema)));
    }

    @SuppressWarnings("unused")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
