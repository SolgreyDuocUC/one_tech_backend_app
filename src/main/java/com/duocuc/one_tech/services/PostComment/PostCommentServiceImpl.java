package com.duocuc.one_tech.services.PostComment;

import com.duocuc.one_tech.dto.post.dto.PostCommentDTO;
import com.duocuc.one_tech.dto.postComment.CreatePostCommentDTO;
import com.duocuc.one_tech.exceptions.PostCommentNotFoundException;
import com.duocuc.one_tech.models.Post;
import com.duocuc.one_tech.models.PostComment;
import com.duocuc.one_tech.models.User;
import com.duocuc.one_tech.repositories.PostCommentRepository;
import com.duocuc.one_tech.repositories.PostRepository;
import com.duocuc.one_tech.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCommentServiceImpl implements PostCommentService {

    private final PostCommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public PostCommentDTO createComment(CreatePostCommentDTO dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post no encontrado: " + dto.getPostId()));

        User author = userRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + dto.getAuthorId()));

        PostComment comment = new PostComment();
        comment.setContent(dto.getContent());
        comment.setPost(post);
        comment.setAuthor(author);
        comment.setIsVisible(dto.getIsVisible() != null ? dto.getIsVisible() : true);
        comment.setCreatedAt(OffsetDateTime.now());

        commentRepository.save(comment);
        return mapToDTO(comment);
    }

    @Override
    public PostCommentDTO getCommentById(Long id) {
        PostComment comment = commentRepository.findById(id)
                .orElseThrow(() -> new PostCommentNotFoundException(id));
        assert comment != null;
        return mapToDTO(comment);
    }

    @Override
    public List<PostCommentDTO> getAllComments() {
        return commentRepository.findAll()
                .stream().filter(Objects::nonNull)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostCommentDTO updateComment(Long id, CreatePostCommentDTO dto) {
        PostComment comment = commentRepository.findById(id)
                .orElseThrow(() -> new PostCommentNotFoundException(id));

        assert comment != null;
        comment.setContent(dto.getContent());
        comment.setIsVisible(dto.getIsVisible() != null ? dto.getIsVisible() : comment.getIsVisible());

        commentRepository.save(comment);
        return mapToDTO(comment);
    }

    @Override
    public void deleteComment(Long id) {
        PostComment comment = commentRepository.findById(id)
                .orElseThrow(() -> new PostCommentNotFoundException(id));
        assert comment != null;
        commentRepository.delete(comment);
    }

    private PostCommentDTO mapToDTO(PostComment comment) {
        return new PostCommentDTO(
                comment.getId(),
                comment.getPost().getId(),
                comment.getAuthor().getId(),
                comment.getAuthor().getName(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}
