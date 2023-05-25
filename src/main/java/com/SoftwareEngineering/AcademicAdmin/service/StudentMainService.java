package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.dto.response.PostResDto;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import com.SoftwareEngineering.AcademicAdmin.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class StudentMainService {

    private final BoardRepository boardRepository;
    public List<PostResDto> getTop5Post(int code){
        List<Post> posts = boardRepository.findLatestPostsByBoardCode(code);
        List<PostResDto> postResDtos = new ArrayList<>();
        for(Post post : posts){
            postResDtos.add(
                    PostResDto.builder()
                            .id(post.getId())
                            .time(post.getTime())
                            .title(post.getTitle())
                            .writer(post.getWriter())
                            .name(post.getBoard().getSubjects().getName())
                            .build());
        }

        return postResDtos;
    }
}
