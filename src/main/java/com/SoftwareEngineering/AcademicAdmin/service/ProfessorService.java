package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.dto.request.SyllabusReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.ScheduleDetailDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.SyllabusResDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
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
    public Long writeSyllabus(SyllabusReqDTO syllabusReqDTO) {
        Optional<Subjects> subjects = subjectsRepository.findById(syllabusReqDTO.getSubjectId());
        Subjects subjects1 = subjects.get();
        subjects1 = Subjects.builder()
                .content(syllabusReqDTO.getSubjectContent())
                .build();

        subjectsRepository.save(subjects1);

        return subjects1.getId();
    }

    public SyllabusResDTO getSyllabus(Long subjectId) {
        Subjects subjects = subjectsRepository.findById(subjectId).get();

        List<ScheduleDetailDTO> scheduleDetailDTOS = new ArrayList<>();

        List<Integer> days = subjects.getDay().chars()
                .mapToObj(Character::getNumericValue)
                .collect(Collectors.toList());

        String[] parts = subjects.getTime().split(",");

        for(int i =0; i<days.size();i++){
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
}
