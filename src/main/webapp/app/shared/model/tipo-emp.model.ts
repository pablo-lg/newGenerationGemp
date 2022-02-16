export interface ITipoEmp {
  id?: number;
  demanda?: boolean | null;
  descripcion?: string | null;
}

export const defaultValue: Readonly<ITipoEmp> = {
  demanda: false,
};
