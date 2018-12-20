FROM nginx:alpine

# Copy the configuration
COPY nginx.conf /etc/nginx/nginx.conf

# Copy the 
WORKDIR /usr/share/nginx/html
COPY dist/camera-app/ .
RUN chmod -R u=rwX,g=rX,o=rX .
