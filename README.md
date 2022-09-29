# Counters-Assignment

The purpose of this assignment is to demonstrate the code

## Expectations

- In a basic Dockerized Springboot Maven application, Write a simple rest-based java application (no frontend solution expected, only backend) that can do the following:
- Create new Counter(s)
- Increment a named counter by 1
- Get a current value of a counter
- Get a list of all counters and their current values
    We should be able to:
- build the application with Maven
- build the Docker image and run it
- make a request to localhost:5000 and verify the behavior


### To build and run the application
 
```sh
mvn clean install
```
The jar will be created in target folder of the application.

To build the image run 
```sh
docker build -t counter .
```
To run the docker image
```sh
docker run -d -p 5000:5000
```

### cURL commands to test the APIs

To create counters
```sh
curl --location --request POST 'localhost:5000/counters/' \
--header 'Content-Type: application/json' \
--data-raw '[
  {
    "name": "First"
  },
  {
    "name": "Second"
  }
]'
```
To increment the counter's value
```sh
curl --location --request PATCH 'localhost:5000/counters/incrementCounterValue/First'
```
To get the counter's value
```sh
curl --location --request GET 'localhost:5000/counters/Second'
```
To fetch the counter's value
```sh
curl --location --request GET 'localhost:5000/counters/'
```
To delete the counter
```sh
curl --location --request DELETE 'localhost:5000/counters/Third'
```