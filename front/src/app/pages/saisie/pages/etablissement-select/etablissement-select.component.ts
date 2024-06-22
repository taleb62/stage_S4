import { Component, OnInit } from '@angular/core';
import { EtablissementService } from 'src/app/services/etablissement.service';

@Component({
  selector: 'app-etablissement-select',
  templateUrl: './etablissement-select.component.html',
  styleUrls: ['./etablissement-select.component.scss']
})
export class EtablissementSelectComponent implements OnInit {

  items: any[] = [];
  selectedItem: any;

  constructor(private etablissementService: EtablissementService) { }

  ngOnInit(): void {
    this.loadEtablissements();
  }

  loadEtablissements(): void {
    this.etablissementService.getEtablissements().subscribe(data => {
      // Ajouter une propriété combinée pour afficher à la fois l'ID et le nom
      this.items = data.map((item: any) => ({
        ...item,
        displayName: `${item.id}  - ${item.nom} `
      }));
      console.log("Les établissements :", this.items);
    });
  }
}
