package com.pshashank.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.pshashank.domain.Post;
import com.pshashank.repositories.PostRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Service("PostServiceImplementation")
public class PostServiceImplementation implements PostService{

    private final PostRepository postRepository;
    private final String ID = "id";
    private final String AUTHORID = "authorId";
    private final String LIKES = "likes";
    private final String POPULARITY = "popularity";
    private final String READS = "reads";

    @Autowired
    PostServiceImplementation(@Qualifier("PostRepository") PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public void addPosts(Post[] posts) {
        for(Post post: posts){
            this.postRepository.addPost(post);
        }
    }

    @Override
    public void addPost(Post post) {
        this.postRepository.addPost(post);
    }

    @Override
    public List<Post> getPosts(String tags, String sortBy, String order)throws IOException, URISyntaxException {

        Map<Float,Post> postMap = fetchData(tags, sortBy);
        List<Post> result = new ArrayList<>();


        if(order.equals("desc")){
            ArrayList<Float> keys = new ArrayList<Float>(postMap.keySet());
            for(int i=keys.size()-1; i>=0;i--){
                result.add(postMap.get(keys.get(i)));
            }
        }
        else if(order.equals("asc")){
            result.addAll(postMap.values());
        }
        else{
            result.addAll(postMap.values());
        }
        return result;
    }


    private Map<Float,Post> fetchData(String tags, String sortBy){
        String[] tagsList = tags.split(",");
        Map<Float,Post> postMap = new TreeMap<Float,Post>();
        try {
            for (String tag : tagsList) {
                URIBuilder b = new URIBuilder("https://api.hatchways.io/assessment/blog/posts");
                b.addParameter("tag", tag);
                URI uri = b.build();
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpGet httpGet = new HttpGet(uri);
                HttpResponse response = httpClient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                StringBuilder content = new StringBuilder();
                if (statusCode == 200) {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    String inputLine;
                    while ((inputLine = rd.readLine()) != null) {
                        content.append(inputLine);
                    }
                    ObjectMapper mapper = new ObjectMapper();
                    JSONObject json = new JSONObject(content.toString());
                    List<Post> posts = mapper.readValue(json.get("posts").toString(),
                            TypeFactory.defaultInstance().constructCollectionType(List.class, Post.class));
                    if(sortBy == null){
                        for (Post post : posts) {
                            if (!postMap.containsKey((float) post.getId()))
                                postMap.put((float) post.getId(), post);
                        }
                    }
                    assert sortBy != null;
                    if(sortBy.equals(AUTHORID)) {
                        for (Post post : posts) {
                            if (!postMap.containsKey((float) post.getAuthorId()))
                                postMap.put((float) post.getAuthorId(), post);
                        }
                    }
                    if(sortBy.equals(LIKES)) {
                        for (Post post : posts) {
                            if (!postMap.containsKey((float) post.getLikes()))
                                postMap.put((float) post.getLikes(), post);
                        }
                    }
                    if(sortBy.equals(POPULARITY)) {
                        for (Post post : posts) {
                            if (!postMap.containsKey((float) post.getPopularity()))
                                postMap.put((float) post.getPopularity(), post);
                        }
                    }
                    if(sortBy.equals(READS)) {
                        for (Post post : posts) {
                            if (!postMap.containsKey((float) post.getReads()))
                                postMap.put((float) post.getReads(), post);
                        }
                    }

                }
            }
        }catch (IOException | URISyntaxException e){
            e.printStackTrace();
        }
        return postMap;
    }
}
