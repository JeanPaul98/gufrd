FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY ../ .
RUN npm install
RUN npm run build

FROM nginx:latest
RUN mkdir /app
COPY --from=build-stage /app/dist/agriculture/browser/ /app
COPY ../nginx.conf /etc/nginx/nginx.conf
USER root
