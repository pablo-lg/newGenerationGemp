import { IEmprendimiento } from 'app/shared/model/emprendimiento.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';

export interface IHistoWF {
  id?: number;
  estadoAnterior?: Estado | null;
  estadoActual?: Estado | null;
  emprendimiento?: IEmprendimiento | null;
}

export const defaultValue: Readonly<IHistoWF> = {};
