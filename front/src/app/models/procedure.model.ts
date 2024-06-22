export interface ProcedurePaa {
  id: number;
  origin: string;
  destinataire: string;
  sourceFinanciere: string;
  description: string;
  deadlineEstime: string;
  montant: number;
  // paaId: number;
  paa: any; 
  verdicts?: any[]; 
}
