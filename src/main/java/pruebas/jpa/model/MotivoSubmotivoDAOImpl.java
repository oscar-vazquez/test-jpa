package pruebas.jpa.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Collections;

public class MotivoSubmotivoDAOImpl implements MotivoSubmotivoDAO {
    private static final Logger logger = LoggerFactory.getLogger(MotivoSubmotivoDAOImpl.class);

    private EntityManager entityManager;

    @Override
    public MotivoSubmotivo findConversionMAC(String motivo, String submotivo) {
        return find(motivo, submotivo, MotivoSubmotivo.Sistema.MAC);
    }

    @Override
    public MotivoSubmotivo findConversionCandela(String motivo, String submotivo) {
        return find(motivo, submotivo, MotivoSubmotivo.Sistema.Candela);
    }

    @Override
    public MotivoSubmotivo find(String motivo, String submotivo, MotivoSubmotivo.Sistema sistema) {
        logger.debug("Buscado motivo {}, submotivo {}", motivo, submotivo);
        return entityManager.find(MotivoSubmotivo.class, new MotivoSubmotivo.MotivoSubmotivoPK(motivo, submotivo, sistema));
    }

    @SuppressWarnings("unused")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
