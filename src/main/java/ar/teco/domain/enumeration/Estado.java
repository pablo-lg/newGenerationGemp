package ar.teco.domain.enumeration;

/**
 * The Estado enumeration.
 */
public enum Estado {
    SIN_ESTADO("Sin estado"),
    PROSPECTO("Prospecto"),
    RELEVADO("Relevado"),
    REQUERIDO("Requerido"),
    EN_OBRA("En obra"),
    TERMINADO("Terminado"),
    SUSPENDIDO("Suspendido"),
    COMPETENCIA("Competencia"),
    CANCELADO("Cancelado"),
    REEMPLAZADO("Reemplazado"),
    EVALUACION_ECONOMICA("Evaluacion economica"),
    PROCESO_FIRMA("Proceso de firma");

    private final String value;

    Estado(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
