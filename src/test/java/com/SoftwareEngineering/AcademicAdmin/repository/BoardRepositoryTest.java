package com.SoftwareEngineering.AcademicAdmin.repository;

import com.SoftwareEngineering.AcademicAdmin.entity.Board;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.security.auth.Subject;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    @DisplayName("최신 공지 5개 확인")
    @Test
    @Transactional
    public void testFindLatestPostsByBoardCode(){
        // Subject 데이터 생성
        Subjects subjects = Subjects.builder()
                .name("SubjectName")
                .credit(3)
                .time(Timestamp.valueOf(LocalDateTime.now()))
                .day("day1")
                .code("SUB1")
                .content("SubjectContent")
                .build();

        subjectsRepository.save(subjects);

        // 테스트용 데이터 생성
        Board board = Board.builder()
                .code(0)
                .subjects(subjects)
                .build();


        boardRepository.save(board);

        for(int i = 0; i<10;i++ ){
            board.getPosts().add(getPost(i,board));
        }

        boardRepository.save(board);

        // 최신 포스트 5개 가져오기
        List<Post> latestPosts = boardRepository.findLatestPostsByBoardCode(0);
        assertEquals(5, latestPosts.size());
        assertEquals("Writer0", latestPosts.get(0).getWriter()); // Writer0가 가장 최신 포스트임

    }

    private Post getPost(int i, Board board){
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