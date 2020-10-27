package com.pshashank.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Post {

    public int id;
    public int authorId;
    public String author;
    public int likes;
    public float popularity;
    public int reads;
    private List<String> tags ;

    public int getId() {
        return id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthor() {
        return author;
    }

    public int getLikes() {
        return likes;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getReads() {
        return reads;
    }

    public List<String> getTags() {
        return tags;
    }



//    public Post(){
//
//    }
//
//    public Post(@JsonProperty("id") int id, @JsonProperty("author") String author,@JsonProperty("authorId") int authorId, @JsonProperty("likes") int likes,@JsonProperty("popularity") float popularity,
//                @JsonProperty("reads") int reads, @JsonProperty("tags") List<String> tags){
//        this.id = id;
//        this.author = author;
//        this.authorId = authorId;
//        this.likes = likes;
//        this.popularity = popularity;
//        this.reads = reads;
//        this.tags = tags;
//    }
}
