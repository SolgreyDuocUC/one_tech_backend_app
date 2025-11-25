package com.duocuc.one_tech.services.Post;


import com.duocuc.one_tech.dto.post.PostDTO;
import com.duocuc.one_tech.dto.post.dto.CreatePostDTO;
import com.duocuc.one_tech.dto.post.dto.UpdatePostDTO;
import com.duocuc.one_tech.exceptions.NotFoundException;
import com.duocuc.one_tech.models.Post;
import com.duocuc.one_tech.repositories.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private PostDTO toDTO(Post entity) {
        return new PostDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getSlug(),
                entity.getExcerpt(),
                entity.getContent(),
                entity.getCoverImageUrl(),
                entity.getIsPublished(),
                entity.getPublishedAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    public PostDTO create(CreatePostDTO dto) {

        if (postRepository.existsBySlug(dto.slug())) {
            throw new IllegalArgumentException("El slug ya existe.");
        }

        Post post = Post.builder()
                .title(dto.title())
                .slug(dto.slug())
                .excerpt(dto.excerpt())
                .content(dto.content())
                .coverImageUrl(dto.coverImageUrl())
                .isPublished(dto.isPublished() != null && dto.isPublished())
                .publishedAt(Boolean.TRUE.equals(dto.isPublished()) ? OffsetDateTime.now() : null)
                .build();

        return toDTO(postRepository.save(post));
    }

    @Override
    public PostDTO findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post no encontrado: " + id));
        assert post != null;
        return toDTO(post);
    }

    @Override
    public List<PostDTO> findAll() {
        return postRepository.findAll()
                .stream().filter(Objects::nonNull)
                .map(this::toDTO)
                .toList();
    }

    @Override
    public PostDTO findBySlug(String slug) {
        Post post = Objects.requireNonNull(postRepository.findBySlug(slug))
                .orElseThrow(() -> new NotFoundException("Post no encontrado (slug): " + slug));
        assert post != null;
        return toDTO(post);
    }

    @Override
    public PostDTO update(Long id, UpdatePostDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post no encontrado: " + id));

        if (dto.title() != null) {
            assert post != null;
            post.setTitle(dto.title());
        }
        if (dto.excerpt() != null) {
            assert post != null;
            post.setExcerpt(dto.excerpt());
        }
        if (dto.content() != null) {
            assert post != null;
            post.setContent(dto.content());
        }
        if (dto.coverImageUrl() != null) {
            assert post != null;
            post.setCoverImageUrl(dto.coverImageUrl());
        }
        if (dto.isPublished() != null) {
            assert post != null;
            post.setIsPublished(dto.isPublished());
            if (dto.isPublished()) post.setPublishedAt(OffsetDateTime.now());
        }

        assert post != null;
        return toDTO(postRepository.save(post));
    }

    @Override
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post no encontrado: " + id));

        assert post != null;
        postRepository.delete(post);
    }
}

