package ar.teco.domain.enumeration;

/**
 * The EstadoFirma enumeration.
 */
public enum EstadoFirma {
    FIRMADO("Firmado"),
    NO_APROBADO("No aprobado"),
    APROBADO_POR_CIC("Aprobado por el CIC");

    private final String value;

    EstadoFirma(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
