FROM golang:1.9-alpine

RUN apk --no-cache add git ca-certificates

WORKDIR ./

COPY app.go .

RUN go get -d -v github.com/go-sql-driver/mysql \
  && CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o app . \
  && cp ./app /root

WORKDIR /root/

CMD ["./app"]
