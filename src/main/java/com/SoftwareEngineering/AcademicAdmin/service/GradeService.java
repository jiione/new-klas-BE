package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.dto.response.GradePerSemesterResDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class GradeService {

    private final UserService userService;
    public List<GradePerSemesterResDTO> getGradePerSemester(Long studentId){
        User user = userService.getUser(studentId);
        
    }
}
