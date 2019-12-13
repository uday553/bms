# bms
BookMyShow Design problem

#### Schema
###### CREATE SCHEMA bms DEFAULT CHARACTER SET utf8 ;

#### Step1: Register Theater
###### (Post) http://localhost:9000/theater/register/v1 
{
	"name":"pvr",
	"address":"bangalore",
	"capacity": 30
}

#### Step2: Reister Movie
###### (Post) http://localhost:9000/movie/register/v1
{
	"name":"Avangers",
	"releaseDate": "2019-12-13"
}
#### Step3: Adding Shows
###### (Post) http://localhost:9000/shows/add/v1
{
	"movieId":1,
	"theaterId": 1
}
#### Step4: Reserving Seats
###### (Post) http://localhost:9000/book/movie/seat/reserve/v1
{
	"showid":1,
	"seatnumbers": [6,9,20],
	"mobileNumber”:9999244923
}
#### Step5: Booking Seats 
###### (Post) http://localhost:9000/book/movie/seat/book/v1
###### Copy Paste the response from previous request
{
	"bookingKey":"2409f8aa7d6b488dd2285d3c1fe7e39d"
}

#### Step6: List Show Booking Details details
######  http://localhost:9000/book/movie/seat/view/v1/1

###### http://localhost:9000/book/movie/seat/view/v1/{showId}

