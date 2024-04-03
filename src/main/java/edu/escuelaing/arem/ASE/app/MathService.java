package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MathService {
    public static void main(String... args){
        port(getPort());
        get("/primes", (req,res) -> {
            Integer num = Integer.parseInt(req.queryParams("value"));
            String outputAsString = calculatePrimes(num).stream()
                                      .map(String::valueOf)
                                      .collect(Collectors.joining(", "));
            return "{\n" +
                    "  \"operation\" :  \"primes\"  ,\n" +
                    "  \"input\" : " + num + ",\n" +
                    "  \"output\" : " + outputAsString + "\n" +
                    "}";
        });
        get("/factors", (req,res) -> {
            Integer num = Integer.parseInt(req.queryParams("value"));
            String outputAsString = calculateFactors(num).stream()
                                      .map(String::valueOf)
                                      .collect(Collectors.joining(", "));
            return "{\n" +
                    "  \"operation\" : \"factors\" ,\n" +
                    "  \"input\" : " + num + ",\n" +
                    "  \"output\" : " + outputAsString + "\n" +
                    "}";
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
