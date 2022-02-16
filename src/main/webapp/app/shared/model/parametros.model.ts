export interface IParametros {
  id?: number;
  clave?: string | null;
  valor?: string | null;
}

export const defaultValue: Readonly<IParametros> = {};
