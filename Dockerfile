FROM node:20-alpine as builder

WORKDIR /app

COPY package.json package-lock.json* ./
RUN npm install

COPY . /app
RUN npm run build 


# production stage 

FROM node:20-slim 

COPY --from=builder ./app  .

EXPOSE 3000

CMD [ "npm" , "start"  ]