import { Component, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { XmlService } from '../service/xml.service';

@Component({
  selector: 'app-resposta-backend-dialog',
  templateUrl: './resposta-backend-dialog.component.html',
  styleUrls: ['./resposta-backend-dialog.component.css'],
  standalone: true,
  imports: [MatButtonModule, MatDialogModule],
})
export class RespostaBackendDialogComponent {
  mensagem: string;

  constructor(public xmlService: XmlService, @Inject(MAT_DIALOG_DATA) private data: any) {
    this.mensagem = data.mensagem;
  }

}
