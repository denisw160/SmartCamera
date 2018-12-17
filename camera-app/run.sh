#!/bin/bash
#
# Run the Angular App for development purpose.
#
# The server for the app is started by angular cli.
# The url for the application is http://localhost:4200
#

echo Running the angular application
ng serve --proxy-config proxy.json
