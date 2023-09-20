import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap, catchError, of, throwError  } from 'rxjs';
import { LoadingService } from '../service/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { RespostaBackendDialogComponent } from '../resposta-backend-dialog/resposta-backend-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class XmlService {
  
  constructor(private http: HttpClient, private loading: LoadingService, public dialog: MatDialog) { }

  xmlString: string = "";

  processarRequisicaoBackend(xml: File): Observable<any> {
  
    console.log("### enviando para o backend o arquivo " + xml.name);
    this.loading.show();

    return new Observable((observer) => {
      if (xml) {
        const reader: FileReader = new FileReader();

        reader.onload = (e: any) => {
          const xmlContent = e.target.result;
          this.xmlString = xmlContent.toString();

          const xmlComPrecoMedioCoberto = this.manterPrecoMedioConfidencial(this.xmlString);

          const formData = new FormData();
          formData.append('file', new Blob([xmlComPrecoMedioCoberto], { type: 'application/xml' }), xml.name);

          return this.http.post('http://localhost:8080/api/agente', formData).subscribe(
            (response: any) => {              
              observer.next(response);
              observer.complete(); 
              this.loading.hide();
              this.dialog.open(RespostaBackendDialogComponent, { data: {"mensagem": response.message}});
            },
            (error) => {              
              observer.error(error); 
              observer.complete(); 
              this.loading.hide();
              this.dialog.open(RespostaBackendDialogComponent, { data: {"mensagem": error.error.message}});
            }
          );
        };
        reader.readAsText(xml);
      }  
    });
    
  }

  manterPrecoMedioConfidencial(xmlContent: string): string {
    const updatedXml = xmlContent.replace(/<precoMedio>[\s\S]*?<\/precoMedio>/g, '');
    //  console.log("xml com preco medio removido:", updatedXml);
    return updatedXml;
  }
}
