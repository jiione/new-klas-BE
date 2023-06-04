package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.dto.request.SyllabusReqDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import com.SoftwareEngineering.AcademicAdmin.repository.SubjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
}
