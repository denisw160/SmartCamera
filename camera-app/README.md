## CameraApp

### Description
This is the web interface of the SmartCamera. The interface is designed for mobile and desktop clients. With this app the pictures are taken or uploaded. You can also go through the captured images and retrieve the recognized data.

This project was generated with [Angular CLI][1] version 7.0.4. The UI components come from [Bootstrap][2].

### Development
#### Development server
Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

The backend service runs http://localhost:80808/. ng serve will redirect all requests .../api to this service and to …/image for the stored images.

#### Code scaffolding
Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

#### Build
Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

#### Running unit tests
Run `ng test` to execute the unit tests via [Karma][3].

#### Running end-to-end tests
Run `ng e2e` to execute the end-to-end tests via [Protractor][4].

#### Further help
To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README][5].

### Scripts
To build the application and the Docker image use this script.

bash\>build.sh

With the optional parameter `-skipDocker` you can skip the Docker build.

To run the local server on Port 4200 you can use this script:

bash\>run.sh

[1]:	https://github.com/angular/angular-cli
[2]:	http://getbootstrap.com
[3]:	https://karma-runner.github.io
[4]:	http://www.protractortest.org/
[5]:	https://github.com/angular/angular-cli/blob/master/README.md