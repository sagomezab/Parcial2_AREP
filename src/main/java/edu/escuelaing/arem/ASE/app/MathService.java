package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

public class MathService {
    public static void main(String... args){
        port(getPort());
        get("/primes", (req,res) -> {
            Integer num = Integer.parseInt(req.queryParams("value"));

            return "{ \"operation\" : \"factors\", \"input\" :" + num + ", \"output\" : \"" + calculatePrimes(num) + "\" }";
        });
        get("/factors", (req,res) -> {
            Integer num = Integer.parseInt(req.queryParams("value"));
            return "{ \"operation\" : \"factors\", \"input\" :" + num + ",  \"output\" : \"" + calculateFactors(num) + "\" }";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001;
    }

    private static List<Integer> calculateFactors(Integer m){
        List<Integer> factors = new ArrayList<>();
        int i = 0;
        for (i = 1; i < m; i++) {
            if (m % i == 0) {
                factors.add(i);
            } else if (i == 1){
                factors.add(1);
            }
        }
        factors.add(m);
        return factors;
    }

    public static List<Integer> calculatePrimes(Integer m){
        List<Integer> primes = new ArrayList<>();
        int i = 0;
        for (i = 1; i < m; i++) {
            if (calculateFactors(i).size() == 2) {
                primes.add(i);
            } 
        }
        return primes;
    }

}
