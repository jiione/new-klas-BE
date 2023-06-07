package com.SoftwareEngineering.AcademicAdmin.controller;

import com.SoftwareEngineering.AcademicAdmin.dto.request.FileReqDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.File;
import com.SoftwareEngineering.AcademicAdmin.repository.FileRepository;
import com.SoftwareEngineering.AcademicAdmin.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    private final FileRepository fileRepository;

    @PostMapping( "/upload")
    public void uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("dto") FileReqDTO fileReqDTO) throws IOException {
        fileService.uploadFile(file, fileReqDTO);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Long id) {
        Optional<File> optionalFileEntity = fileRepository.findById(id);
        if (optionalFileEntity.isPresent()) {
            File file = optionalFileEntity.get();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", file.getFileName());

            return new ResponseEntity<>(file.getFileData(), headers, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }
}
