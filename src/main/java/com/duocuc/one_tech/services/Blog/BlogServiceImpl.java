package com.duocuc.one_tech.services.Blog;

import com.duocuc.one_tech.dto.post.*;
import com.duocuc.one_tech.dto.post.dto.*;
import com.duocuc.one_tech.exceptions.NotFoundException;
import com.duocuc.one_tech.models.Post;
import com.duocuc.one_tech.models.PostComment;
import com.duocuc.one_tech.models.User;
import com.duocuc.one_tech.repositories.PostCommentRepository;
import com.duocuc.one_tech.repositories.PostRepository;
import com.duocuc.one_tech.repositories.TagRepository;
import com.duocuc.one_tech.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;


    public BlogServiceImpl(PostRepository postRepository,
                           PostCommentRepository postCommentRepository,
                           TagRepository tagRepository,
                           UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    // POSTS

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> listPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post no encontrado con id " + id));
        return PostMapper.toDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO getPostBySlug(String slug) {
        Post post = postRepository.findBySlug(slug)
                .orElseThrow(() -> new NotFoundException("Post no encontrado con slug " + slug));
        return PostMapper.toDto(post);
    }

    // TAGS

    @Override
    @Transactional(readOnly = true)
    public List<TagDTO> listTags() {
        return tagRepository.findAll()
                .stream()
                .map(TagMapper::toDto)
                .toList();
    }

    // COMMENTS

    @Override
    @Transactional(readOnly = true)
    public List<PostCommentDTO> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post no encontrado con id " + postId));

        return postCommentRepository.findByPost(post)
                .stream()
                .map(PostCommentMapper::toDto)
                .toList();
    }

    @Override
    public PostCommentDTO addComment(Long postId, Long userId, PostCommentRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post no encontrado con id " + postId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con id " + userId));

        PostComment comment = new PostComment();
        comment.setPost(post);
        comment.setAuthor(user);
        comment.setContent(request.comment());

        try {
            comment.setCreatedAt(OffsetDateTime.now());
        } catch (Exception ignored) {}

        PostComment saved = postCommentRepository.save(comment);
        return PostCommentMapper.toDto(saved);
    }

    @Override
    public void deleteComment(Long commentId) {
        if (!postCommentRepository.existsById(commentId)) {
            throw new NotFoundException("Comentario no encontrado con id " + commentId);
        }
        postCommentRepository.deleteById(commentId);
    }
    @Override
    public PostDTO updatePost(Long id, PostUpdateDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));

        // Actualizar solo los campos que vengan en el DTO (puedes hacerlo obligatorio si quieres)
        if (dto.title() != null)          post.setTitle(dto.title());
        if (dto.slug() != null)           post.setSlug(dto.slug());
        if (dto.excerpt() != null)        post.setExcerpt(dto.excerpt());
        if (dto.content() != null)        post.setContent(dto.content());
        if (dto.coverImageUrl() != null)  post.setCoverImageUrl(dto.coverImageUrl());

        if (dto.isPublished() != null) {
            post.setIsPublished(dto.isPublished());

            if (Boolean.TRUE.equals(dto.isPublished()) && post.getPublishedAt() == null) {
                post.setPublishedAt(OffsetDateTime.now());
            }
            if (Boolean.FALSE.equals(dto.isPublished())) {
                post.setPublishedAt(null);
            }
        }

        Post saved = postRepository.save(post);
        return PostMapper.toDto(saved);
    }
}
