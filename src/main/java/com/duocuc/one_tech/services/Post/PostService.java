package com.duocuc.one_tech.services.Post;

import com.duocuc.one_tech.dto.post.PostDTO;
import com.duocuc.one_tech.dto.post.dto.CreatePostDTO;
import com.duocuc.one_tech.dto.post.dto.UpdatePostDTO;

import java.util.List;

public interface PostService {

    PostDTO create(CreatePostDTO dto);

    PostDTO findById(Long id);

    List<PostDTO> findAll();

    PostDTO findBySlug(String slug);

    default PostDTO update(Long id, UpdatePostDTO dto) {
        return null;
    }

    void delete(Long id);
}
