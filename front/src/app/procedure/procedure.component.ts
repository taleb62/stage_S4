import { Component, OnInit } from '@angular/core';
import { ProcedurePaaService } from '../services/procedure.service';
import { ProcedurePaa } from '../models/procedure.model';

@Component({
  selector: 'app-procedure',
  templateUrl: './procedure.component.html',
  styleUrls: ['./procedure.component.scss']
})
export class ProcedureComponent implements OnInit {
  procedures: ProcedurePaa[] = [];

  constructor(private procedurePaaService: ProcedurePaaService) {}

  ngOnInit(): void {
    this.getProcedures();
  }

  getProcedures(): any {
    this.procedurePaaService.getAllProcedures().subscribe(
      (data: ProcedurePaa[]) => {
        this.procedures = data;
      },
      (error) => {
        console.error('Error fetching procedures', error);
      }
    );
    
  }

  
  
}
