package springboot.rest.api.blog.service;

import springboot.rest.api.blog.payload.PostDto;
import springboot.rest.api.blog.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void detelePost(long id);
}
