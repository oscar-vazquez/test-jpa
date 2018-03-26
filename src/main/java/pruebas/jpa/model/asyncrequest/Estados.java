package pruebas.jpa.model.asyncrequest;

public enum Estados implements IEstado {
    RECIBIDO {
        @Override
        public Estados next() {
            return DESPACHADO;
        }

        @Override
        public boolean canMoveTo(Estados estado) {
            return estado == next() || estado == ERROR;
        }
    },
    DESPACHADO {
        @Override
        public Estados next() {
            return GENERAR_ORDEN;
        }


        @Override
        public boolean canMoveTo(Estados estado) {
            return estado == next() || estado == ERROR;
        }
    },
    GENERAR_ORDEN {
        @Override
        public Estados next() {
            return EN_PROCESO;
        }

        @Override
        public boolean canMoveTo(Estados estado) {
            return estado == next() || estado == ERROR;
        }
    },
    EN_PROCESO {
        @Override
        public Estados next() {
            return FINALIZADO;
        }

        @Override
        public boolean canMoveTo(Estados estado) {
            return estado == next() || estado == ERROR;
        }
    },
    FINALIZADO {
        @Override
        public Estados next() {
            throw new RuntimeException("Estado final");
        }

        @Override
        public boolean canMoveTo(Estados estado) {
            return estado == next() || estado == ERROR;
        }
    },
    ERROR_GEN_ORDEN {
        @Override
        public Estados next() {
            throw new RuntimeException("Estado final");
        }

        @Override
        public boolean canMoveTo(Estados estado) {
            return estado == next() || estado == ERROR;
        }
    },
    ERROR {
        @Override
        public Estados next() {
            throw new RuntimeException("Estado final");
        }

        @Override
        public boolean canMoveTo(Estados estado) {
            return false;
        }
    };

    @Override
    public String toString() {
        return super.toString().replace('_', ' ');
    }

    public static Estados getEnum(String name) {
        String x = name.replace(' ', '_');
        return Estados.valueOf(x);
    }

    public static Estados getInitialStare() {
        return RECIBIDO;
    }
}
