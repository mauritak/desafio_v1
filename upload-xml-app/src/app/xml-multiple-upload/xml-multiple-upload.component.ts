import { Component } from '@angular/core';
import { XmlService } from '../service/xml.service';
import { LoadingService } from '../service/loading.service';

@Component({
  selector: 'app-xml-multiple-upload',
  templateUrl: './xml-multiple-upload.component.html',
  styleUrls: ['./xml-multiple-upload.component.css']
})
export class XmlMultipleUploadComponent {
  xmls: File[] = [];
  uploadedXmls: string[] = [];

  constructor(private xmlService: XmlService, public loadingService: LoadingService) { }

  onFileSelected(event: any) {
    this.xmls = Array.from(event.target.files);

    // ordenar arquivos xmls pelo nome
    this.xmls.sort((a, b) => {
      const regex = /_(\d+)\.xml/;
      const matchA = a.name.match(regex);
      const matchB = b.name.match(regex);

      if (matchA && matchB) {
        const numA = parseInt(matchA[1]);
        const numB = parseInt(matchB[1]);
        return numA - numB;
      }
      return 0;
    });

    // imprimi a ordem dos arquivos xml pelo nome
    this.xmls.forEach((xmlFile) => {
      console.log(`Nome do xml carregado pelo file upload: ${xmlFile.name}`);
    });
  }

  submit() {
    for (const xmlContent of this.xmls) {
      this.xmlService.processarRequisicaoBackend(xmlContent).subscribe(
        (response) => {
          console.log('Resposta do backend:', response);
        },
        (error) => {
          console.error('Erro ao enviar solicitação para o backend', error.error.mensagem);
        }
      );
    }
  }
}
