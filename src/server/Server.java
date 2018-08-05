package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import server.controllers.AddSalary;
import server.controllers.GetMoney;
import server.controllers.GetSalary;
import server.controllers.acceptTravelByDriver;
import server.controllers.addMoney;
import server.controllers.cancelTravel;
import server.controllers.createTravel;
import server.controllers.driverLogin;
import server.controllers.driverTravelKindCount;
import server.controllers.getDriverIdByUsername;
import server.controllers.getDriverTravels;
import server.controllers.getPassengerIdByUsername;
import server.controllers.getPassengerTravels;
import server.controllers.passengerLogin;
import server.controllers.passengerTravelKindCount;
import server.controllers.setFinish;
import server.controllers.setpoint;
import server.controllers.signup;

public class Server {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/driver/login", new driverLogin()); //done 
        server.createContext("/passenger/login", new passengerLogin());//done
        server.createContext("/signup", new signup());//done
        server.createContext("/driver/travelkindsCount", new driverTravelKindCount());//done
        server.createContext("/driver/getIdByUsername", new getDriverIdByUsername());//done
        server.createContext("/driver/getSalary", new GetSalary());//not complete(need to set in application!)	
        server.createContext("/passenger/travelkindsCount", new passengerTravelKindCount());//done
        server.createContext("/passenger/setpoint", new setpoint());//done
        server.createContext("/passenger/getIdByUsername", new getPassengerIdByUsername());
        server.createContext("/passenger/getmoney", new GetMoney());//done
        server.createContext("/passenger/addMoney", new addMoney());//done
        server.createContext("/travel/AcceptTravelByDriver", new acceptTravelByDriver());//done
        server.createContext("/travel/create", new createTravel());//done
        server.createContext("/travel/cancel", new cancelTravel());//done
        server.createContext("/travel/getDriverTravels", new getDriverTravels());//done
        server.createContext("/travel/getPassengerTravels", new getPassengerTravels());//done
        server.createContext("/travel/setfinish", new setFinish());//done
        server.setExecutor(null);
        server.start();
    }


}