import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SERVER_URL_BE} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class DirectoryService {
  constructor(private http: HttpClient, ) { }


  getAllDossier(): Observable<any> {
    return this.http.get(SERVER_URL_BE+ 'Dossier/getAllDossier');
  }
  getLetters(id:any): Observable<any> {
    return this.http.get(SERVER_URL_BE+ 'Dossier/getLettres/'+id);
  }
  getLettresByQuery(id:any): Observable<any> {
    return this.http.get(SERVER_URL_BE+ 'Dossier/LettresNotInPlis/'+id);
  }

  download(file: string | undefined,fileSubFolder:any): Observable<Blob> {
    return this.http.get(`${SERVER_URL_BE}uploadFiles/${file}`, {
      responseType: 'blob',
      params:{'fileSubFolder':fileSubFolder}
    });
  }
  createLetters(data:any): Observable<any> {
    return this.http.post(SERVER_URL_BE+ 'Dossier/createLettre',data);
  }}
