package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

public class Factores {

    public static void main(String... args){
        port(getPort());
        get("/factors", (req,res) -> {
            Integer num = Integer.parseInt(req.queryParams("value"));
            return "{ \"operation\" : \"factors\", \"input\" :" + num + ",  \"output\" : \"" + calculateFactors(num) + "\" }";
        });
    }
    

    private static int getPort() {
      if (System.getenv("PORT") != null) {
          return Integer.parseInt(System.getenv("PORT"));
      }
      return 5002;
    } 

    public static List<Integer> calculateFactors(Integer m){
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
}
