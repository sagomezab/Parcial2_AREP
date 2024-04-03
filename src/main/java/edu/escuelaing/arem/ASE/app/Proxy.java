package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;

public class Proxy {

    private static RoundRobin invoker = new RoundRobin();

    public static void main(String... args){
        staticFileLocation("/public");
        port(getPort());
        get("/primes", (req,res) -> {
            res.type("application/json");
            return invoker.invokePrimes(req.queryParams("value"));
            
        });
        get("/factors", (req,res) -> {
            res.type("application/json");
            return invoker.invokeFactors(req.queryParams("value"));
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
