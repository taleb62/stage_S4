import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProcedurePaa } from '../models/procedure.model';

@Injectable({
  providedIn: 'root'
})
export class ProcedurePaaService {
  private apiUrl = 'http://localhost:8089/api/rest/procedure';

  constructor(private http: HttpClient) {}

  getAllProcedures(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get<any>(this.apiUrl, { headers });
  }

  createProcedure(paaId: number, procedure: ProcedurePaa): Observable<ProcedurePaa> {
    const headers = this.getHeaders();
    return this.http.post<ProcedurePaa>(`${this.apiUrl}/${paaId}`, procedure, { headers });
  }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token'); 
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }
}
