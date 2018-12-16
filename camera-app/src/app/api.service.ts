import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

import {environment} from '../environments/environment';
import {ImageModel} from './model/image.model';
import {ImageDetailModel} from './model/image-detail.model';
import {UploadModel} from './model/upload.model';
import {UploadResponseModel} from './model/uploadResponse.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  private readonly mockUpMode: boolean;

  constructor(private _http: HttpClient) {
    this.mockUpMode = environment.mockUpMode;
    // console.debug('Run services in mockUp mode: ' + this.mockUpMode);
  }

  /**
   * Query all stored images from the backend.
   */
  getImages(): Observable<ImageModel[]> {
    if (this.mockUpMode) {
      return this._http.get<ImageModel[]>('/assets/mock_api_images.json');
    } else {
      return this._http.get<ImageModel[]>('/api/images');
    }
  }

  /**
   * Query all details for the image.
   */
  getImageDetails(imageId: string): Observable<ImageDetailModel[]> {
    if (this.mockUpMode) {
      return this._http.get<ImageDetailModel[]>('/assets/mock_api_image-details.json');
    } else {
      return this._http.get<ImageDetailModel[]>('/api/image/' + imageId + '/details');
    }
  }

  /**
   * Post the data with the image to the backend.
   * The server return the state of the transmission.
   *
   * @param upload Parameter
   */
  uploadImage(upload: UploadModel): Observable<UploadResponseModel> {
    if (this.mockUpMode) {
      return this._http.get<UploadResponseModel>('/assets/mock_api_upload-response.json');
    } else {
      return this._http.post<UploadResponseModel>('/api/upload', upload, this.httpOptions);
    }
  }

}
