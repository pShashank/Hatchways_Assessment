package com.pshashank.repositories;

import com.pshashank.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("PostRepository")
public class PostRepositoryImplementation implements PostRepository {

    //Non-persitant database
    private static List<Post> postList = new ArrayList<>();
//    private static Map<String, List<Post>> searchmap = new TreeMap<String, ArrayList<Post>>();

    @Override
    public Post[] findAll() {
        return null;
    }

    @Override
    public void addPost(Post post) {
        postList.add(post);
    }
}
