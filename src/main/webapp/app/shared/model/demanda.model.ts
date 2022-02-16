import { ITipoEmp } from 'app/shared/model/tipo-emp.model';
import { IDespliegue } from 'app/shared/model/despliegue.model';

export interface IDemanda {
  id?: number;
  a1?: string | null;
  a2?: string | null;
  a3?: string | null;
  a4?: string | null;
  a5?: string | null;
  tipoEmp?: ITipoEmp | null;
  despliegue?: IDespliegue | null;
}

export const defaultValue: Readonly<IDemanda> = {};
