package pruebas.jpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(AsyncRequestXtra.EntityListener.class)
@Table(name = "async_request_xtra")
public class AsyncRequestXtra {
    @Embeddable
    static class PK implements Serializable {
        @Column(name = "numero_orden", length = 15, nullable = false, updatable = false, unique = true)
        protected String numeroOrden;

        @Column(name = "dato", nullable = false, updatable = false)
        protected String dato;

        @Override
        public int hashCode() {
            final int prime = 31;
            int hash = 17;
            hash = hash * prime + this.numeroOrden.hashCode();
            hash = hash * prime + this.dato.hashCode();

            return hash;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PK)) {
                return false;
            }
            PK castOther = (PK)other;
            return this.numeroOrden.equals(castOther.numeroOrden) && this.dato.equals(castOther.dato);
        }
    }

    @EmbeddedId
    private PK id;

    @Column(name = "valor", nullable = false)
    private String valor;

    @Transient
    private AsyncRequest asyncRequest;


    AsyncRequestXtra() {}

    AsyncRequestXtra(AsyncRequest asyncRequest, String dato, String valor) {
        setAsyncRequest(asyncRequest);
        setDato(dato);
        setValor(valor);
    }


    void setAsyncRequest(AsyncRequest asyncRequest) {
        this.asyncRequest = asyncRequest;
        if (id == null) {
            id = new PK();
        }
        id.numeroOrden = asyncRequest.getNumeroOrden();
    }

    public String getDato() {
        return id.dato;
    }

    void setDato(String dato) {
        if (id == null) {
            id = new PK();
        }
        id.dato = dato;
    }

    public String getValor() {
        return valor;
    }

    void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AsyncRequestLog{");
        sb.append("numeroOrden=").append(id.numeroOrden);
        sb.append(", dato=").append(id.dato);
        sb.append(", valor=").append(valor);
        sb.append('}');
        return sb.toString();
    }

    static public class EntityListener {
        @PrePersist
        public void setNumeroOrden(final AsyncRequestXtra entity) {
            if (entity.id.numeroOrden == null) {
                entity.id.numeroOrden = entity.asyncRequest.getNumeroOrden();
            }
        }
    }

}
