export interface IGrupoEmp {
  id?: number;
  descripcion?: string | null;
  esProtegido?: boolean | null;
}

export const defaultValue: Readonly<IGrupoEmp> = {
  esProtegido: false,
};
