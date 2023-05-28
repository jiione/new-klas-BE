package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.dto.response.PostResDto;
import com.SoftwareEngineering.AcademicAdmin.entity.Course;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import com.SoftwareEngineering.AcademicAdmin.repository.BoardRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class StudentMainService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    public List<PostResDto> getTop5Post(Long studentId) {
        User user = userService.getUser(studentId);

        List<Course> courses = userService.getCourse(user);
        List<Post> posts = new ArrayList<>();

        for (Course course : courses) {
            List<Post> coursePosts = boardRepository.findLatestPostsByBoardCodeAndSubjectId(0, course.getSubjects().getId());
            posts.addAll(coursePosts);
        }

        List<Post> sortedPosts = posts.stream()
                .sorted(Comparator.comparing(Post::getTime).reversed())
                .limit(5)
                .collect(Collectors.toList());

        List<PostResDto> postResDtos = new ArrayList<>();
        for (Post post : sortedPosts) {
            postResDtos.add(PostResDto.builder()
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
