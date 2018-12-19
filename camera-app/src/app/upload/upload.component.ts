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
  loading: boolean;

  constructor(private _api: ApiService) {
  }

  ngOnInit() {
  }


  onFileSelected(event) {
    this.selectedFile = event.target.files[0];
  }

  onUpload() {
    const upload = new UploadModel();
    upload.name = this.selectedFile.name;

    this.loading = true;
    this._api.uploadImage(upload, this.selectedFile).subscribe(r => {
      this.success = r.success;
      this.loading = false;
    }, () => {
      this.success = false;
      this.loading = false;
    });
  }

}
