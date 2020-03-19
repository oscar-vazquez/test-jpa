package pruebas.jpa.model;

public interface MotivoSubmotivoDAO {
    MotivoSubmotivo find(String motivo, String submotivo, String sistema);
}
