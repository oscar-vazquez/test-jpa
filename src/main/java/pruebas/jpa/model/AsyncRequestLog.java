package pruebas.jpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(AsyncRequestLog.EntityListener.class)
@Table(name = "async_request_log")
public class AsyncRequestLog {
    @Embeddable
    static class PK implements Serializable {
        @Column(name = "numero_orden", length = 15, nullable = false, updatable = false, unique = true)
        protected String numeroOrden;

        @Column(name = "seqno", nullable = false, updatable = false)
        protected Integer sequence;

        @Override
        public int hashCode() {
            final int prime = 31;
            int hash = 17;
            hash = hash * prime + this.numeroOrden.hashCode();
            hash = hash * prime + this.sequence.hashCode();

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
            return this.numeroOrden.equals(castOther.numeroOrden) && this.sequence.equals(castOther.sequence);
        }
    }

    @EmbeddedId
    private PK id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "log_dttm", nullable = false)
    private Date   logDateTime = new Date();

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "motivo", nullable = false)
    private String motivo;

    @Column(name = "descr", nullable = false)
    private String descripcion;

    @Transient
    private AsyncRequest asyncRequest;


    AsyncRequestLog() {}

    AsyncRequestLog(AsyncRequest asyncRequest, String motivo, String descripcion) {
        setAsyncRequest(asyncRequest);
        setMotivo(motivo);
        setDescripcion(descripcion);
    }

    public Date getLogDateTime() {
        return logDateTime;
    }

    public String getEstado() {
        return estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    void setAsyncRequest(AsyncRequest asyncRequest) {
        this.asyncRequest = asyncRequest;
        if (id == null) {
            id = new PK();
        }
        id.numeroOrden = asyncRequest.getNumeroOrden();
        id.sequence = asyncRequest.getLog().size() + 1;
        estado = asyncRequest.getEstado().toString();
    }

    void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AsyncRequestLog{");
        sb.append("numeroOrden=").append(id.numeroOrden);
        sb.append(", sequence=").append(id.sequence);
        sb.append(", logDateTime=").append(logDateTime);
        sb.append(", estado='").append(estado).append('\'');
        sb.append(", motivo='").append(motivo).append('\'');
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append('}');
        return sb.toString();
    }

    static public class EntityListener {
        @PrePersist
        public void setNumeroOrden(final AsyncRequestLog entity) {
            if (entity.id.numeroOrden == null) {
                entity.id.numeroOrden = entity.asyncRequest.getNumeroOrden();
            }
        }
    }

}
