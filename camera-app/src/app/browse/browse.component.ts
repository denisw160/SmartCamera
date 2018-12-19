import {Component, OnDestroy, OnInit} from '@angular/core';

import {ApiService} from '../api.service';
import {ImageModel} from '../model/image.model';
import {ImageDetailModel} from '../model/image-detail.model';
import {interval} from 'rxjs';

@Component({
  selector: 'app-browse',
  templateUrl: './browse.component.html',
  styleUrls: ['./browse.component.css']
})
export class BrowseComponent implements OnInit, OnDestroy {

  timer = null;

  images: ImageModel[];
  details: ImageDetailModel[];

  selectedImage: ImageModel;

  constructor(private _api: ApiService) {
  }

  ngOnInit() {
    this._api.getImages().subscribe(images => this.images = images);

    const interval1 = interval(15000); // update every 15s
    this.timer = interval1.subscribe(n => {
      this._api.getImages().subscribe(images => this.images = images);
    });
  }

  ngOnDestroy(): void {
    if (this.timer != null) {
      this.timer.unsubscribe();
    }
  }

  onSelect($event: MouseEvent, image: ImageModel) {
    this.selectedImage = image;
    this._api.getImageDetails(image.id).subscribe(details => this.details = details);
  }

}
