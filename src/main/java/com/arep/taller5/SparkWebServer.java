package com.arep.taller5;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.Spark.get;

public class SparkWebServer {

    public static void main(String... args) {

        staticFiles.location("/public");

        
        port(getPort());
        get("hello", (req, res) -> "Hello Docker!");

        get("sin", (req, res) -> {
            Double x = Double.valueOf(req.queryParams("x"));
            Double response = Math.sin(x);
            return response;
            }
        );

        get("cos", (req, res) -> {
            Double x = Double.valueOf(req.queryParams("x"));
            Double response = Math.cos(x);
            return response;
            }
        );

        get("palindrome", (req, res) -> {
            String word = req.queryParams("word");
            boolean response = palindrome(word);
            return response;
            }
        );

        get("magnitude", (req, res) -> {
            String x = req.queryParams("x");
            String y = req.queryParams("y");

            int x1 = Integer.parseInt(x.split(",")[0]);
            int x2 = Integer.parseInt(x.split(",")[1]);
            int y1 = Integer.parseInt(y.split(",")[0]);
            int y2 = Integer.parseInt(y.split(",")[1]);

            int dX = x1-y1;
            int dY = x2-y2;

            Double response = Math.sqrt((dX*dX)+(dY*dY));
            return response;
            }
        );
    }

    private static boolean palindrome(String word) {
        int begin = 0;
        int end = word.length()-1;

        int delta = 0;
        boolean response = true;
        int max = word.length()/2;

        while(response && delta<=max){
            response = word.charAt(begin+delta)==word.charAt(end-delta)?true:false;
            delta++;
        }

        return response;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}