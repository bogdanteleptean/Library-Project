package com.sda.practical.services;

import com.sda.practical.databases.AuthorEntity;
import com.sda.practical.databases.AuthorRepository;
import com.sda.practical.databases.BookEntity;
import com.sda.practical.databases.BookRepository;
import com.sda.practical.utils.LoggerUtils;

import java.util.List;
import java.util.Scanner;

public class BookService extends BaseService{
    public void addBook(Scanner scanner, BookRepository bookRepository, AuthorRepository authorRepository) {
        LoggerUtils.print("Authors list: ");
        AuthorRepository.getAllAuthors().forEach(System.out::println);
        LoggerUtils.print("Select an author from list by entering his first name and last name ");
        LoggerUtils.print("Enter author first name: ");
        String authorFirstName = scanner.nextLine();
        LoggerUtils.print("Enter author last name: ");
        String authorLastName = scanner.nextLine();

        if (AuthorRepository.findByName(authorFirstName, authorLastName) != null){
            LoggerUtils.print("Enter book title: ");
            String bookTitle = scanner.nextLine();
            BookEntity bookEntity = new BookEntity();
            bookEntity.setBookTitle(bookTitle);

            List<AuthorEntity> authorEntities = AuthorRepository.findByName(authorFirstName, authorLastName);
            for (AuthorEntity a: authorEntities) {
                bookEntity.setAuthorEntity(a);
                bookRepository.addBook(bookEntity);
            }
        }
        else {
            LoggerUtils.print("Author not found! Give an author from list!");
        }
    }


    public void findBookAndAuthor(Scanner scanner, BookRepository bookRepository) {
        LoggerUtils.print("Enter book title");
        String bookTitle = scanner.nextLine();
        List<BookEntity> books = BookRepository.findBookByTitle(bookTitle);
        if(books.isEmpty() || books == null){
            LoggerUtils.print("Book not found!");
        }
        else {
            books.stream().forEach(System.out::println);
        }
    }

    public List<BookEntity> findBooksByAuthor(Scanner scanner, BookRepository bookRepository){
        LoggerUtils.print("Enter author first name");
        String authorFirstName = scanner.nextLine();
        LoggerUtils.print("Enter author last name");
        String authorLastName = scanner.nextLine();
        List<BookEntity> bookEntities =  BookRepository.findAllBooksByAuthorName(authorFirstName,authorLastName);
        if(AuthorRepository.findByName(authorFirstName, authorLastName) == null){
            LoggerUtils.print("Author not found !");
        }
        else if (bookEntities.isEmpty()){
            LoggerUtils.print("No books found by " + authorLastName);
        }
        else {
            bookEntities.stream().forEach(System.out::println);
        }
        return bookEntities;
    }

    public void updateBook(Scanner scanner, BookRepository bookRepository) {
        LoggerUtils.print("Select the book you wish to update from the list, using the id");
        displayAllBooksWithAuthors();
        Integer id = Integer.parseInt(scanner.nextLine());
        LoggerUtils.print("Enter the new book title:");
        String bookTitle = scanner.nextLine();
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(id);
        bookEntity.setBookTitle(bookTitle);
        bookEntity.setAuthorEntity(bookRepository.findById(id).getAuthorEntity());
        bookRepository.updateBook(bookEntity);
    }

    public void deleteBook(Scanner scanner, BookRepository bookRepository) {
        LoggerUtils.print("Enter book id");
        Integer id = Integer.parseInt(scanner.nextLine());
        BookEntity bookEntity = bookRepository.findById(id);
        bookRepository.deleteBook(bookEntity);
    }

    public void displayAllBooksWithAuthors(){
        BookRepository.getAllBooks().stream().forEach(System.out::println);
    }
}
