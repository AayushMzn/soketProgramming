package org.example;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        String s = "12:45:54PM";
        int hour = parseInt(s.split(":")[0]);
        String response = "";
        if (s.endsWith("PM")) {
            String returnHour = s.split("P")[0];
            if (hour == 12) {
                response = s.split("P")[0];
            }else {
                hour = hour + 12;
                response = hour + ":" + returnHour.split(":", 2)[1];
                if (hour == 24) {
                    response = "00" + ":" + returnHour.split(":", 2)[1];
                }
            }
        } else {
            String returnHour = s.split("A")[0];
            if (s.startsWith("12")) {
                response = "00" + ":" + returnHour.split(":", 2)[1];
            } else {
                response = s.split("A")[0];
            }
        }
        System.out.println(response);
    }
}


