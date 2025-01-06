package mo.kobweb.drivererror.api

import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

@InitApi
fun initAPI(ctx: InitApiContext) {
    val logger = ctx.logger

    /*
        Stackoverflow link with suggested manually loading Driver. Saw somewhere else this is not necessary anymore, but figured trying.
        https://stackoverflow.com/a/62436701/26808738

        If added here, "val connection" successfully connects however JOOQ is still unable to find driver.
        If NOT added here, "connection" & DriverManager are unable to find suitable jdbc driver, throws error.
     */
//    Class.forName("org.postgresql.Driver")

    val databaseUrl = "jdbc:postgresql://localhost:5432/postgres"
    val username = "postgres"
    val password = "5432"
    val connection: Connection

    try {
        //If no manual driver load on line 22, receive error: No suitable driver found for jdbc:postgresql://localhost:5432/postgres
        connection = DriverManager.getConnection(databaseUrl, username, password)
        logger.debug("~!!!~ PSQL Connected!")

        //If manual driver loaded, receive error here: java.lang.NullPointerException: findResource(...) must not be null
//        val jooqConnection = DSL.using(connection, SQLDialect.POSTGRES) //^ Assuming this findResource() is not finding the driver.
        logger.debug("~!!!~ JOOQ Connected!") //Won't Reach

    } catch (e: SQLException) {
        logger.debug("~!!!~ PSQL Exception: ${e.message} | $e")
    }
}