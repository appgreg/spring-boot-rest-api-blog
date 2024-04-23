package springboot.rest.api.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springboot.rest.api.blog.entity.Post;
import springboot.rest.api.blog.exception.ResourceNotFoundException;
import springboot.rest.api.blog.payload.PostDto;
import springboot.rest.api.blog.payload.PostResponse;
import springboot.rest.api.blog.repository.PostRepository;
import springboot.rest.api.blog.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = this.mapToEntity(postDto);
        Post newPost = this.postRepository.save(post);

        return this.mapToDTO(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(sortDir) ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> postsPage = this.postRepository.findAll(pageable);
        List<Post> postList = postsPage.getContent();

        List<PostDto> content = postList.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(pageNo);
        postResponse.setPageSize(pageSize);
        postResponse.setTotalElements((int) postsPage.getTotalElements());
        postResponse.setTotalPages(postsPage.getTotalPages());
        postResponse.setLast(postsPage.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = this.postRepository.save(post);

        return mapToDTO(updatedPost);
    }

    @Override
    public void detelePost(long id) {
        Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        this.postRepository.delete(post);
    }

    private PostDto mapToDTO(Post post){
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    private Post mapToEntity(PostDto postDto){
        Post post =  modelMapper.map(postDto, Post.class);
        return post;
    }
}
