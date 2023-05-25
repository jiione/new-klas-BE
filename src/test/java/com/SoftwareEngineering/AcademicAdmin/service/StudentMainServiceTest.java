package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.dto.response.PostResDto;
import com.SoftwareEngineering.AcademicAdmin.entity.Board;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import com.SoftwareEngineering.AcademicAdmin.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentMainServiceTest {
    private StudentMainService studentMainService;

    @Mock
    private BoardRepository boardRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        studentMainService = new StudentMainService(boardRepository);
    }

    @Test
    public void testGetTop5Post() {
        // 가짜 데이터 생성
        Subjects subjects = Subjects.builder()
                .name("Math")
                .build();

        Board board = Board.builder()
                .code(0)
                .subjects(subjects)
                .build();

        List<Post> posts = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            posts.add(getPost(i,board));
        }

        // Mock 객체 설정
        when(boardRepository.findLatestPostsByBoardCode(0)).thenReturn(posts);

        // 테스트 실행
        List<PostResDto> result = studentMainService.getTop5Post(0);

        // 결과 검증
        assertEquals(5, result.size());
        assertEquals("Title1", result.get(0).getTitle());
        assertEquals("Writer1", result.get(0).getWriter());
        assertEquals("Math", result.get(0).getName());
        assertEquals("Title2", result.get(1).getTitle());
        assertEquals("Writer2", result.get(1).getWriter());
        assertEquals("Math", result.get(1).getName());
    }

    private Post getPost(int i, Board board) {
        return Post.builder()
                .writer("Writer" + String.valueOf(i))
                .time(LocalDateTime.now().minusHours(i))
                .content("Content" + String.valueOf(i))
                .title("Title" + String.valueOf(i))
                .view(10L)
                .board(board)
                .build();
    }
}