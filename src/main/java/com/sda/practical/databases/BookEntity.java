package com.sda.practical.databases;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(length = 25)
    private String bookTitle;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private AuthorEntity authorEntity;

    public BookEntity() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public AuthorEntity getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(AuthorEntity authorEntity) {
        this.authorEntity = authorEntity;
    }

    @Override
    public String toString() {
        return "Book{" +
                " Id=" + bookId +
                ", Title='" + bookTitle + '\'' +
                ", " + authorEntity +
                '}';
    }
}
