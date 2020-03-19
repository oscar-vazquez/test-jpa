package pruebas.jpa.model;

public interface MotivoSubmotivoDAO {
    MotivoSubmotivo findConversionMAC(String motivo, String submotivo);
    MotivoSubmotivo findConversionCandela(String motivo, String submotivo);
    MotivoSubmotivo find(String motivo, String submotivo, MotivoSubmotivo.Sistema sistema);
}
