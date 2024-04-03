package edu.escuelaing.arem.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Hello world!
 *
 */
public class RoundRobin {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String[] MATH_SERVICES = new String[]{
        "http://ec2-3-80-149-91.compute-1.amazonaws.com:5001/",
        "http://ec2-54-166-239-182.compute-1.amazonaws.com:5001/"
    };
    int instance = 0;
    public String invokeFactors(String args) throws IOException {

        String encodedLog = URLEncoder.encode(args, "UTF-8");
        String GET_URL = MATH_SERVICES[instance]+"factors?value="+ encodedLog;
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        moveinstance();
        return response.toString();
    }

    public String invokePrimes(String args) throws IOException {

        String encodedLog = URLEncoder.encode(args, "UTF-8");
        String GET_URL = MATH_SERVICES[instance]+"primes?value="+ encodedLog;
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        moveinstance();
        return response.toString();
    }

    public void moveinstance(){
        if(instance < 1){
            instance++;
        }else {
            instance = 0;
        }

    }

}
