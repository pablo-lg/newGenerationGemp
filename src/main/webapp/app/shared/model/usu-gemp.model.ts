export interface IUsuGemp {
  id?: number;
  usu?: string | null;
  nombre?: string | null;
  apellido?: string | null;
  email?: string | null;
  perfiles?: string | null;
}

export const defaultValue: Readonly<IUsuGemp> = {};
