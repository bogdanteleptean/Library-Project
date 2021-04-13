package com.sda.practical;

import com.sda.practical.views.MainViewHandler;

/**
 * Book management system
 *
 * -aplicatie
 *
 * tine intr-o baza de date cartile si autorii dintr-o librarie
 *
 * -aplicatia trebuie sa aiba o interfata pentru:
 * 1-adaugare de autori
 * 2-adaugare de carti la autor
 * 3-afisarea cartilor, autorilor, cartilor unui autor specific
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new MainViewHandler().startApp();
    }
}
