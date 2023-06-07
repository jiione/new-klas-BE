package com.SoftwareEngineering.AcademicAdmin.controller;

import com.SoftwareEngineering.AcademicAdmin.entity.File;
import com.SoftwareEngineering.AcademicAdmin.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("postId") Long id) throws IOException {
        File uploadedFile = fileService.uploadFile(file,id);
        return ResponseEntity.ok(uploadedFile);
    }
}
