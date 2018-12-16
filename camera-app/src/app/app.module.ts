import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UploadComponent} from './upload/upload.component';
import {ErrorComponent} from './error/error.component';
import {BrowseComponent} from './browse/browse.component';
import {ApiService} from './api.service';

@NgModule({
  declarations: [
    AppComponent,
    ErrorComponent,
    UploadComponent,
    BrowseComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [ApiService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
