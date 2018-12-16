import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UploadComponent} from './upload/upload.component';
import {ErrorComponent} from './error/error.component';

const routes: Routes = [
  {path: 'upload', component: UploadComponent},
  {path: '', redirectTo: '/upload', pathMatch: 'full'},
  {path: '**', component: ErrorComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes,
    {
      enableTracing: true // <-- debugging purposes only
    })],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
