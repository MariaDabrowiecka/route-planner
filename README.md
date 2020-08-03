# route-planner

PoC of system for planning the train route.

Connections between stations can be registered by introducing:
* origin station
* final station
* departure time
* arrival time

Input from the user:
* travel date and time
* departure station
* arrival station 

Example output:

```
Route from: Lodz Widzew to: Gdansk Glowny
Number of changes: 1
Total time in minutes: 240
From: Lodz Widzew, to: Poznan Glowny, Departure time: Thu Jan 22 13:15:00 CET 2015, Arrival time: Thu Jan 22 14:15:00 CET 2015
From: Poznan Glowny, to: Gdansk Glowny, Departure time: Thu Jan 22 14:15:00 CET 2015, Arrival time: Thu Jan 22 17:15:00 CET 2015
```

The PoC version of the system has been happy path tested in [here](https://github.com/MariaDabrowiecka/route-planner/blob/master/src/test/java/com/dabrowiecka/routeplanner/InMemoryTrainRoutePlannerServiceTest.java)

Please run:
```
mvn clean install
```




