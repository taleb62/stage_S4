import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { PaaService } from 'src/app/services/paa.service';

@Component({
  selector: 'app-offcanvas',
  templateUrl: './offcanvas.component.html',
  styleUrls: ['./offcanvas.component.scss']
})
export class OffcanvasComponent implements OnInit {

  detaille: any;
  user: any;

  constructor(private paaService: PaaService, private auth: AuthenticationService) { }

  ngOnInit(): void {
  }
  showOffcanvas = false;

  toggleOffcanvas() {
    this.showOffcanvas = !this.showOffcanvas;
    if (this.showOffcanvas) {
      // Remplacez '1' par l'ID réel que vous souhaitez récupérer
      // this.detaillePaa(id); 
    }
  }

  detaillePaa(id: any):any {
    this.paaService.getPaaOne(id).subscribe({
      next: (value) => {
        this.detaille = value;
        console.log(value);
        // console.log("les roles de user  : "+user);
      },
      error: (e) => {
        console.log(e);
      }
    });
    
    this.user=this.auth.isAdmin();

    
    
  }
}
