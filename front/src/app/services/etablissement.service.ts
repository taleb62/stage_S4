import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EtablissementService {
  private apiUrl = 'http://localhost:8089/api/rest/etablissements';

  constructor(private http: HttpClient) { }

  getEtablissements(): Observable<any[]> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any[]>(this.apiUrl, { headers });
  }

<<<<<<< HEAD
  

=======
  addEtablissement(etablissement: any): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post<any>(this.apiUrl, etablissement, { headers });
  }

  updateEtablissement(etablissement: any): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put<any>(`${this.apiUrl}/${etablissement.id}`, etablissement, { headers });
  }

  deleteEtablissement(id: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.delete<any>(`${this.apiUrl}/${id}`, { headers });
  }
>>>>>>> b40cbddf2a78569cc471baf24aa952e82c1a00e7
}
