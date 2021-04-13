package com.sda.practical.views;

import java.util.ArrayList;
import java.util.List;

public class MenuHandler {
    //pastreaza si afiseaza doar meniul aplicatiei mele
    public List<String> getMainMenu(){
        List<String> menu = new ArrayList<>();
        menu.add("1. Add Author");
        menu.add("2. Edit Author");
        menu.add("3. Delete Author");
        menu.add("4. Add Book");
        menu.add("5. Search Author");
        menu.add("6. Search Book by Title");
        menu.add("7. Edit Book");
        menu.add("8. Delete Book");
        menu.add("9. Find Books by Author Name");
        menu.add("10. Delete Author and all his books");
        menu.add("11. Display Books Collection");
        menu.add("12. Exit and Clear Authors Without Books");

        return menu;
    }
}
