package com.pshashank.repositories;

import com.pshashank.domain.Post;

public interface PostRepository {

    Post[] findAll();
    void addPost(Post post);
}
