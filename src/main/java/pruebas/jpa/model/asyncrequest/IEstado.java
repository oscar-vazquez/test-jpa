package pruebas.jpa.model.asyncrequest;

public interface IEstado {
    Estados next();

    boolean canMoveTo(Estados estado);
}
