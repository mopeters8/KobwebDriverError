[Link to issue in Kobweb Repo <-](https://github.com/varabyte/kobweb/issues/638)

This is a reproduction project trying to figure out a postgres driver error.
Code is inside Startup.kt on the JVM/API side.

My Goal: I would like to be able to use JOOQ with PostgreSQL in Kobweb API. 
I usually use separate Ktor backend, but being able to use it here for smaller projects keeping both in one repo would be neat.

Situation: Whenever I try to create the JOOQ connection in code, errors fill my console, and it is unable to start up. 
The most top error says there is no SL4J logging, but I don't think it's necessary/relevant. 
I found another error deep, and it says: "java.lang.NullPointerException: findResource(...) must not be null". Perhaps this is JOOQ unable to find the driver?

Not manually loading the driver leads to no suitable jdbc driver error on the DriverManager line. (Line 31)
Removing the JOOQ connection line & manually loading the driver early in code, I am successfully able to make
the connection with the DriverManager (Line 31), but breaks when it gets time for JOOQ connection (Line 35).

Discord link to thread I started discussing issue, and request for issue:
https://discord.com/channels/886036660767305799/886039480316858368/1320831754088747160

Feel free to contact me!
