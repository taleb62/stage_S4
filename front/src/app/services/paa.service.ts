import { Injectable } from '@angular/core';
import { SERVER_URL_BE2,SERVER_URL_BE3, SERVER_URL_BE, SERVER_URL_FE,SERVER_URL_BEPing } from '../../environments/environment';
import {HttpClient, HttpHeaders,HttpErrorResponse } from "@angular/common/http";

import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class PaaService {

  constructor(private http: HttpClient,) { }
  
  getPaa(): Observable<any> {
    return this.http.get(SERVER_URL_BE+ 'Paa/getAllPaaa');
  }
  getPaaOne(id:any): Observable<any> {
    return this.http.get(SERVER_URL_BE+ `Paa/getPaaa/${id}`);
  }
  
  addPaa(paa: any): Observable<any> {
    return this.http.post(SERVER_URL_BE + 'Paa/addPaa', paa);
  }
  modifierPaa(id: number, paaData: any): Observable<any> {
    return this.http.put(`${SERVER_URL_BE}Paa/modifier/${id}`, paaData);
  }
  validerPaa(id: any): Observable<any> {
    return this.http.put(SERVER_URL_BE + `Paa/valider/${id}`, id);
  }
  supprimerPaa(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8089/api/rest/Paa/deletePaa/${id}`);
  }

  declancchementPost(data:any): Observable<any> {
    return this.http.post(SERVER_URL_BE+ 'Paa/updatePaa',data);
  }

  validateCreateDir(data:any): Observable<any> {
    return this.http.post(SERVER_URL_BE+ 'Dossier/create',data);
  }

  private apiUrl = 'http://localhost:8089/api/rest/Paa/upload';

 
  uploadFile(file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);

    return this.http.post(this.apiUrl, formData, {
      headers: new HttpHeaders({
        'Accept': 'application/json'
      })
    }).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Unknown error!';
    if (error.error instanceof ErrorEvent) {
      // Client-side errors
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side errors
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }



//   generateReport(id:any){
//     const headers = new HttpHeaders({
//       responseType: 'blob'
//     });
//   return this.http.get(SERVER_URL_BE+ 'Paa/report/'+id, {headers});
// }
  telechargerPieceJointe(id: string,reportName:any): Observable<any> {
    const url =SERVER_URL_BE+ 'Dossier/report/'+id;
    const headers = new HttpHeaders({
      Accept: 'application/pdf'
    });

    return this.http.get(url, {headers, responseType: 'blob',  params:{'report-name':reportName}});
  }

}
