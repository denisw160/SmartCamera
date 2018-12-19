import {Component, OnInit} from '@angular/core';

import {ApiService} from '../api.service';
import {ImageModel} from '../model/image.model';
import {ImageDetailModel} from '../model/image-detail.model';

@Component({
  selector: 'app-browse',
  templateUrl: './browse.component.html',
  styleUrls: ['./browse.component.css']
})
export class BrowseComponent implements OnInit {

  images: ImageModel[];
  details: ImageDetailModel[];

  selectedImage: ImageModel;

  constructor(private _api: ApiService) {
  }

  ngOnInit() {
    this._api.getImages().subscribe(images => this.images = images);
  }

  onSelect($event: MouseEvent, image: ImageModel) {
    this.selectedImage = image;
    this._api.getImageDetails(image.id).subscribe(details => this.details = details);
  }

  // TODO AutoRefresh images

}
