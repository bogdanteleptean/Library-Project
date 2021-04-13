package com.sda.practical.services;

import com.sda.practical.utils.LoggerUtils;

import java.util.Scanner;

public abstract class BaseService {
    protected Boolean confirmationFromUser(Scanner scanner){
        LoggerUtils.print("Are you sure ? (Yes / No)");
        String option = scanner.nextLine();
        if("Yes".equalsIgnoreCase(option)){
            return true;
        }
        return false;
    }
}
