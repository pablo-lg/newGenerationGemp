package ar.teco.domain.enumeration;

/**
 * The EstadoBC enumeration.
 */
public enum EstadoBC {
    APROBADO("Aprobado"),
    NO_APROBADO("No aprobado");

    private final String value;

    EstadoBC(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
