# Flight-management-system

## Example Requests
1: **One way Search the flight**

##### GET  localhost:8081/flight/search?source=delhi&destination=mumbai&departureDate=29/08/20
Request
````
curl -X GET \
  'http://localhost:8081/flight/search?source=delhi&destination=mumbai&departureDate=29%2F08%2F20' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 9ef5a27e-d7b2-9bee-431c-78f01fbd87e7'
````
Response
```json
[
    {
        "id": 5,
        "source": "delhi",
        "destination": "mumbai",
        "departureDate": "29/08/20 08:00:00",
        "arrivalDate": "29/08/20 10:00:00",
        "flightName": "flight3",
        "flightNo": "F-3",
        "price": 3000,
        "capacity": 300
    },
    {
        "id": 3,
        "source": "delhi",
        "destination": "mumbai",
        "departureDate": "29/08/20 12:00:00",
        "arrivalDate": "29/08/20 14:00:00",
        "flightName": "flight2",
        "flightNo": "F-2",
        "price": 4000,
        "capacity": 200
    }
]
```

2: **two way Search the flight**

##### GET  localhost:8081/flight/search?source=delhi&destination=pune&departureDate=29/08/20&returnDate=30/08/20&flightWay=2
Request
````
curl -X GET \
  'http://localhost:8081/flight/search?source=delhi&destination=pune&departureDate=29%2F08%2F20&returnDate=30%2F08%2F20&flightWay=2' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: ca2733bc-8942-1ae8-de05-0ab20459f3d8'
````
Response
```json
[
    {
        "id": 1,
        "source": "delhi",
        "destination": "pune",
        "departureDate": "29/08/20 12:00:00",
        "arrivalDate": "29/08/20 14:00:00",
        "flightName": "flight1",
        "flightNo": "F-1",
        "price": 3000,
        "capacity": 100
    },
    {
        "id": 2,
        "source": "pune",
        "destination": "delhi",
        "departureDate": "30/08/20 15:00:00",
        "arrivalDate": "30/08/20 17:00:00",
        "flightName": "flight1",
        "flightNo": "F-1",
        "price": 3500,
        "capacity": 100
    }
]
```



3.  **To flight booking user**

@params : userId(required),flightId(required)

##### POST localhost:8081/flight/{userId}/booking?flightId=1
Request:- 
```json
{
"firstName":"Name",
  "lastName":"Garg",
  "mobileNo":"9599376588",
  "emailId":"arpitgarg39@gmail.com",
 "travellerCount":400
}
```
Response:- 
```json
{
    "passengerId": 1,
    "noOfPassenger": 400,
    "discount": 60000,
    "finalAmount": 1140000,
    "user": {
        "userId": 1,
        "firstName": "Name",
        "lastName": "Garg",
        "mobileNo": "9599376588",
        "emailId": "arpitgarg39@gmail.com",
        "userType": {
            "userTypeId": 1,
            "userType": "silver",
            "discount": 5
        }
    },
    "flight": {
        "id": 1,
        "source": "delhi",
        "destination": "pune",
        "departureDate": "29/08/20 12:00:00",
        "arrivalDate": "29/08/20 14:00:00",
        "flightName": "flight1",
        "flightNo": "F-1",
        "price": 3000,
        "capacity": 100
    },
    "totalAmount": 1200000
}```