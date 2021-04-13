package com.sda.practical.services;

import com.sda.practical.databases.AuthorEntity;
import com.sda.practical.databases.AuthorRepository;
import com.sda.practical.databases.BookEntity;
import com.sda.practical.databases.BookRepository;
import com.sda.practical.utils.LoggerUtils;

import java.awt.print.Book;
import java.util.List;
import java.util.Scanner;

public class AuthorService extends BaseService {
    public void addAuthor(Scanner scanner, AuthorRepository authorRepository) {
        LoggerUtils.print("Enter author first name");
        String firstName = scanner.nextLine();
        LoggerUtils.print("Enter author last name");
        String lastName = scanner.nextLine();
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setAuthorFirstName(firstName);
        authorEntity.setAuthorLastName(lastName);
        authorRepository.addAuthor(authorEntity);
    }

    public void updateAuthor(Scanner scanner, AuthorRepository authorRepository) {
        LoggerUtils.print("Enter author id");
        Integer id = Integer.parseInt(scanner.nextLine());
        LoggerUtils.print("Enter author first name");
        String firstName = scanner.nextLine();
        LoggerUtils.print("Enter author last name");
        String lastName = scanner.nextLine();
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setAuthorId(id);
        authorEntity.setAuthorFirstName(firstName);
        authorEntity.setAuthorLastName(lastName);
        authorRepository.updateAuthor(authorEntity);
    }

    public List<AuthorEntity> findAuthor(Scanner scanner, AuthorRepository authorRepository) {
        LoggerUtils.print("Enter author first name");
        String firstName = scanner.nextLine();
        LoggerUtils.print("Enter author last name");
        String lastName = scanner.nextLine();
        if (AuthorRepository.findByName(firstName, lastName) != null) {
            LoggerUtils.print("Found");
            AuthorRepository.findByName(firstName, lastName).forEach(System.out::println);

            return AuthorRepository.findByName(firstName, lastName);
        }
        System.out.println("Author not found !");
        return null;
    }

    public void deleteAuthor(Scanner scanner, AuthorRepository authorRepository) {
        LoggerUtils.print("Enter author first name");
        String firstName = scanner.nextLine();
        LoggerUtils.print("Enter author last name");
        String lastName = scanner.nextLine();
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setAuthorFirstName(firstName);
        authorEntity.setAuthorLastName(lastName);
        if (AuthorRepository.findByName(firstName, lastName) == null){
            LoggerUtils.print("Author not found !");
            return;
        }

        if (confirmationFromUser(scanner)) {
            List<AuthorEntity> authorEntityList = AuthorRepository.findByName(firstName, lastName);
            for (AuthorEntity a : authorEntityList) {
                List<BookEntity> bookList = BookRepository.findAllBooksWithAuthorId(a.getAuthorId());
                if (bookList == null || bookList.isEmpty()) {
                    authorRepository.deleteAuthor(a);
                    LoggerUtils.print("Author deleted");
                } else {
                    LoggerUtils.print("Cannot delete author : delete his books first !");
                }
            }
        }
    }

    public void deleteAuthorAndHisBooks(Scanner scanner, AuthorRepository authorRepository, BookRepository bookRepository) {
        LoggerUtils.print("Enter author first name");
        String firstName = scanner.nextLine();
        LoggerUtils.print("Enter author last name");
        String lastName = scanner.nextLine();
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setAuthorFirstName(firstName);
        authorEntity.setAuthorLastName(lastName);

        if (AuthorRepository.findByName(firstName, lastName) == null){
            LoggerUtils.print("Author not found !");
            return;
        }

        if (!confirmationFromUser(scanner)) {
            return;
        }

        List<AuthorEntity> authorEntityList = AuthorRepository.findByName(firstName, lastName);
        for (AuthorEntity a : authorEntityList) {
            List<BookEntity> bookList = BookRepository.findAllBooksWithAuthorId(a.getAuthorId());
            if (bookList == null || bookList.isEmpty()) {
                authorRepository.deleteAuthor(a);
            }
            else {
                for (BookEntity b: bookList) {
                    bookRepository.deleteBook(b);
                }
                authorRepository.deleteAuthor(a);
                LoggerUtils.print(lastName + "'s books were deleted");
            }
        }
    }

    public void deleteAuthorsWithoutBooks(AuthorRepository authorRepository){
        List<AuthorEntity> authorList = AuthorRepository.getAllAuthors();
        for (AuthorEntity a: authorList) {
            if(BookRepository.findAllBooksByAuthorName(a.getAuthorFirstName(),a.getAuthorLastName()).isEmpty()){
                LoggerUtils.print(a + " was deleted!");
                authorRepository.deleteAuthor(a);
            }
        }
    }

}
