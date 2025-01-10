# frontend/Dockerfile
FROM node:14
WORKDIR /app
COPY . .
RUN rm -rf node_modules  && npm install
RUN npm install axios
CMD ["npm", "start"]

