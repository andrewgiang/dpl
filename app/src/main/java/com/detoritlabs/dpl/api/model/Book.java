
package com.detoritlabs.dpl.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Book implements Parcelable {

    
    private List<Publisher> publishers = new ArrayList<Publisher>();
    
    private String subtitle;
    
    private String title;
    
    private String url;
    @SerializedName("number_of_pages")
    
    private Integer numberOfPages;
    
    private Cover cover;
    @SerializedName("publish_date")
    
    private String publishDate;
    
    private String key;
    
    private List<Author> authors = new ArrayList<Author>();
    
    private List<Excerpt> excerpts = new ArrayList<Excerpt>();
    private List<Subject> subjects = new ArrayList<Subject>();

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Excerpt> getExcerpts() {
        return excerpts;
    }

    public void setExcerpts(List<Excerpt> excerpts) {
        this.excerpts = excerpts;
    }

    public List<Subject> getSubjects(){
        return subjects;
    }
    public void setSubjects(List<Subject> subjects){
        this.subjects = subjects;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(publishers);
        dest.writeString(this.subtitle);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeValue(this.numberOfPages);
        dest.writeParcelable(this.cover, 0);
        dest.writeString(this.publishDate);
        dest.writeString(this.key);
        dest.writeTypedList(this.authors);
        dest.writeTypedList(excerpts);
        dest.writeTypedList(subjects);
    }

    public Book() {
    }

    private Book(Parcel in) {
        in.readTypedList(publishers, Publisher.CREATOR);
        this.subtitle = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.numberOfPages = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cover = in.readParcelable(Cover.class.getClassLoader());
        this.publishDate = in.readString();
        this.key = in.readString();
        in.readTypedList(this.authors, Author.CREATOR);
        in.readTypedList(excerpts, Excerpt.CREATOR);
        in.readTypedList(subjects, Subject.CREATOR);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


    public static class BookResponseDeserializer implements JsonDeserializer<BookResponse>{

        @Override
        public BookResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            BookResponse response = new BookResponse();
            JsonObject asJsonObject = json.getAsJsonObject();
            asJsonObject.toString();
            Set<Map.Entry<String, JsonElement>> entries = asJsonObject.entrySet();
            Iterator<Map.Entry<String, JsonElement>> iterator = entries.iterator();
            if(iterator.hasNext()){
                Map.Entry<String, JsonElement> next = iterator.next();

                String ISBKEY = next.getKey();
                JsonObject jsonElement = asJsonObject.getAsJsonObject(ISBKEY);
                Book book = context.deserialize(jsonElement, Book.class);
                response.setBook(book);
            }
            return response;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "excerpts=" + excerpts +
                ", authors=" + authors +
                ", key='" + key + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", cover=" + cover +
                ", numberOfPages=" + numberOfPages +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", publishers=" + publishers +
                '}';
    }
}
