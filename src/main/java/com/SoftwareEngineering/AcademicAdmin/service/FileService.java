package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.entity.File;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import com.SoftwareEngineering.AcademicAdmin.exception.post.PostNotFound;
import com.SoftwareEngineering.AcademicAdmin.repository.FileRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {

    private final FileRepository fileRepository;

    private final PostRepository postRepository;

    public File uploadFile(MultipartFile multipartFile, Long id) throws IOException {
        Optional<Post> postOptional = postRepository.findById(id);
        if(postOptional.isPresent()){
            // 파일 메타데이터 저장
            File file = File.builder()
                    .fileName(multipartFile.getName())
                    .fileData(multipartFile.getBytes())
                    .post(postOptional.get())
                    .build();
            fileRepository.save(file);

            return file;
        }
        else {
            throw new PostNotFound();
        }

    }
}
