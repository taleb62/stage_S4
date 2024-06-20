import { Injectable } from '@angular/core';
import { SERVER_URL_BE2,SERVER_URL_BE3, SERVER_URL_BE, SERVER_URL_FE,SERVER_URL_BEPing } from '../../environments/environment';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaaService {

  constructor(private http: HttpClient, ) { }
  getPaa(): Observable<any> {
    return this.http.get(SERVER_URL_BE+ 'Paa/getAllPaaa');
  }
  addPaa(paa: any): Observable<any> {
    return this.http.post(SERVER_URL_BE + 'Paa/addPaa', paa);
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
