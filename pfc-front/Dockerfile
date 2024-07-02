# Use node image to build the frontend
FROM node:18 AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Use nginx image to serve the frontend
FROM nginx:alpine
COPY --from=build /app/dist/spa /usr/share/nginx/html
EXPOSE 80
