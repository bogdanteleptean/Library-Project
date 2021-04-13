package com.sda.practical.utils;

import java.util.List;

public class LoggerUtils {
    //partea de logare in cadrul aplicatie
    public static void print(String message) {
        System.out.println(message);
    }
    public static void print(List message){
        message.stream().forEach(System.out::println);
    }
}
