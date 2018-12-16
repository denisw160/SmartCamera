import {Component, OnInit} from '@angular/core';
import {ApiService} from '../api.service';
import {UploadModel} from '../model/upload.model';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  selectedFile: File = null;
  success: boolean = null;

  constructor(private _api: ApiService) {
  }

  ngOnInit() {
  }


  onFileSelected(event) {
    this.selectedFile = event.target.files[0];
  }

  onUpload() {
    console.log(this.selectedFile); // You can use FormData upload to backend server

    const upload = new UploadModel();
    upload.file = this.selectedFile;
    upload.name = this.selectedFile.name;
    this._api.uploadImage(upload).subscribe(r => this.success = r.success);
  }

}
