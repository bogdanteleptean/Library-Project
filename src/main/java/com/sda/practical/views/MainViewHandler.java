package com.sda.practical.views;

import com.sda.practical.databases.AuthorRepository;
import com.sda.practical.databases.BookEntity;
import com.sda.practical.databases.BookRepository;
import com.sda.practical.services.AuthorService;
import com.sda.practical.services.BookService;
import com.sda.practical.utils.KeyboardUtils;
import com.sda.practical.utils.LoggerUtils;

import java.util.Scanner;

public class MainViewHandler {
    //afiseaza meniul si va cere optiune de la utilizator

    public void startApp() {
        LoggerUtils.print("Start App");

        Scanner scanner = new Scanner(System.in);
        MenuHandler menuHandler = new MenuHandler();
        AuthorService authorService = new AuthorService();
        AuthorRepository authorRepository = new AuthorRepository();
        BookService bookService = new BookService();
        BookRepository bookRepository = new BookRepository();

        Integer option = 0;
        while (option != 12) {
            //display menu
            LoggerUtils.print(menuHandler.getMainMenu());
            //get option from user

            option = KeyboardUtils.readNumber(scanner,"Select an option :");
            //scanner.nextLine();
            switch (option) {
                case 1:
                    authorService.addAuthor(scanner, authorRepository);
                    break;
                case 2:
                    authorService.updateAuthor(scanner, authorRepository);
                    break;
                case 3:
                    authorService.deleteAuthor(scanner, authorRepository);
                    break;
                case 4:
                    bookService.addBook(scanner, bookRepository, authorRepository);
                    break;
                case 5:
                    authorService.findAuthor(scanner, authorRepository);
                    break;
                case 6:
                    bookService.findBookAndAuthor(scanner, bookRepository);
                    break;
                case 7:
                    bookService.updateBook(scanner, bookRepository);
                    break;
                case 8:
                    bookService.deleteBook(scanner, bookRepository);
                    break;
                case 9:
                    bookService.findBooksByAuthor(scanner, bookRepository);
                    break;
                case 10:
                    authorService.deleteAuthorAndHisBooks(scanner, authorRepository, bookRepository);
                    break;
                case 11:
                    bookService.displayAllBooksWithAuthors();
                    break;
                case 12:
                    authorService.deleteAuthorsWithoutBooks(authorRepository);
                    return;

                default:
                    LoggerUtils.print("Option does not exist");
            }
        }
    }
}
