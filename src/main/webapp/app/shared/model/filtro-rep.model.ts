export interface IFiltroRep {
  id?: number;
  nombre?: string | null;
  filtro?: string | null;
}

export const defaultValue: Readonly<IFiltroRep> = {};
