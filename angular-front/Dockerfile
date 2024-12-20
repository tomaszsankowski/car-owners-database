FROM nginx:alpine

ENV API_URL=http://localhost:8080/api

EXPOSE 80

ADD ./dist/angular-front /usr/share/nginx/html
ADD docker/etc/nginx/templates/ /etc/nginx/templates/
