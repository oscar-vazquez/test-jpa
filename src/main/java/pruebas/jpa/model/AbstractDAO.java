package pruebas.jpa.model;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class AbstractDAO<T> {
    protected Class<T> entityClass;
    protected EntityManager entityManager;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @SuppressWarnings("unused")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persist(T entity) {
        entityManager.persist(entity);
    }

    public T merge(T entity) {
        return entityManager.merge(entity);
    }

    public void remove(Object entityId) {
        T entity = find(entityId);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    public T find(Object id) {
        return entityManager.find(entityClass, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
    }

    public List<T> findAll() {
        CriteriaQuery<T> cq = entityManager.getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return entityManager.createQuery(cq).getResultList();
    }
}
