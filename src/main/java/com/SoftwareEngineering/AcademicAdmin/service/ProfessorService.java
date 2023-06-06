package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.dto.request.PostReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.request.SyllabusReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.ScheduleDetailDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.SyllabusResDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Board;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import com.SoftwareEngineering.AcademicAdmin.exception.board.BoardNotFound;
import com.SoftwareEngineering.AcademicAdmin.exception.subject.SubjectNotFound;
import com.SoftwareEngineering.AcademicAdmin.repository.BoardRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.PostRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.SubjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfessorService {

    private final SubjectsRepository subjectsRepository;

    private final BoardRepository boardRepository;

    private final PostRepository postRepository;

    public Long writeSyllabus(SyllabusReqDTO syllabusReqDTO) {
        Optional<Subjects> subjectOptional = subjectsRepository.findById(syllabusReqDTO.getSubjectId());
        System.out.println("aaaaaaa");

        if(subjectOptional.isPresent()) {
            Subjects subjects = subjectOptional.get();

            subjectsRepository.save(Subjects.builder()
                            .professor(subjects.getProfessor())
                            .id(subjects.getId())
                            .name(subjects.getName())
                            .credit(subjects.getCredit())
                            .time(subjects.getTime())
                            .day(subjects.getDay())
                            .code(subjects.getCode())
                            .content(syllabusReqDTO.getSubjectContent())
                            .year(subjects.getYear())
                            .semester(subjects.getSemester())
                            .personnel(subjects.getPersonnel())
                    .build());

            return subjects.getId();
        }
        else{
            throw new SubjectNotFound();
        }

    }

    public SyllabusResDTO getSyllabus(Long subjectId) {
        Optional<Subjects> subjectsOptional = subjectsRepository.findById(subjectId);

        if(subjectsOptional.isPresent()) {
            Subjects subjects = subjectsOptional.get();
            List<ScheduleDetailDTO> scheduleDetailDTOS = new ArrayList<>();

            List<Integer> days = subjects.getDay().chars()
                    .mapToObj(Character::getNumericValue)
                    .collect(Collectors.toList());

            String[] parts = subjects.getTime().split(",");

            for (int i = 0; i < days.size(); i++) {
                ScheduleDetailDTO scheduleDetailDTO = new ScheduleDetailDTO();
                scheduleDetailDTO.setDay(days.get(i));
                for (char ch : parts[i].toCharArray()) {
                    int digit = Character.getNumericValue(ch);
                    scheduleDetailDTO.getTime().add(digit);
                }

                scheduleDetailDTOS.add(scheduleDetailDTO);
            }

            return SyllabusResDTO.builder()
                    .professor(subjects.getProfessor())
                    .when(scheduleDetailDTOS)
                    .code(subjects.getCode())
                    .name(subjects.getName())
                    .credit(subjects.getCredit())
                    .personnel(subjects.getPersonnel())
                    .content(subjects.getContent())
                    .build();
        }
        else {
            throw new SubjectNotFound();
        }
    }

    public Long writePost(PostReqDTO postReqDTO) {
        Board board = boardRepository.findBoardByCodeAndSubjectId(postReqDTO.getCode(),postReqDTO.getSubjectId());
        if(board != null){
            Post post = Post.builder()
                    .view(0L)
                    .writer(postReqDTO.getWriter())
                    .title(postReqDTO.getTitle())
                    .content(postReqDTO.getContent())
                    .deadline(postReqDTO.getDeadline())
                    .board(board)
                    .build();

            postRepository.save(post);

            return post.getId();
        }
        else {
            throw new BoardNotFound();
        }

    }
}
