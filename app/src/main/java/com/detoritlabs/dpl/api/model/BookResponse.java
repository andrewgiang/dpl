
package com.detoritlabs.dpl.api.model;


public class BookResponse {

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookResponse{" +
                "book=" + book +
                '}';
    }
}
