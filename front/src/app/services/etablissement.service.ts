import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_URL_BE } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EtablissementService {

  private apiUrl = 'etablissements';

  constructor(private http: HttpClient) { }

  

  getEtablissements(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get(SERVER_URL_BE + this.apiUrl, { headers });
  }

  

}
