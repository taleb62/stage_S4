import { Component, OnInit, ViewChild } from '@angular/core';
import { GridComponent, RowDeselectEventArgs, RowSelectEventArgs } from '@syncfusion/ej2-angular-grids';
import { EtablissementService } from 'src/app/services/etablissement.service';

@Component({
  selector: 'app-etablissement',
  templateUrl: './etablissement.component.html',
  styleUrls: ['./etablissement.component.scss']
})
export class EtablissementComponent implements OnInit {

  selected: Object[] = null;
  public pageSettings: Object;

  @ViewChild('grid') public grid: GridComponent;

  constructor(private etablisement: EtablissementService) {
    this.pageSettings = { pageSize: 10 };

  }

  ngOnInit(): void {
  }




  // etablissements(): any{
  //   this.etablisement.getEtablissements().subscribe({
  //     next: (value)=>{
  //       console.log("les etablissemnts : "+value);
        
  //     }
  //   });
  // }

  // rowSelected(args: RowSelectEventArgs) {
  //   console.log(this.grid.getSelectedRecords())
  //   this.selected = this.grid.getSelectedRecords();
  //   // console.log("le paa : " + this.selected[0]["id"]);
  //   // this.paaId = this.selected[0]["id"];

  // }

  // rowDeselected($event: RowDeselectEventArgs) {
  //   this.selected = null;
  //   // console.log(this.selected)
  // }



}
