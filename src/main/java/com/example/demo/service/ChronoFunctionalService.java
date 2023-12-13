package com.example.demo.service;

import com.example.demo.entity.ChronoFunctional;
import com.example.demo.repository.ChronoFunctionalRepository;
import com.example.demo.util.FileCompressor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChronoFunctionalService {

    @Autowired
    private ChronoFunctionalRepository repository;

    public List<String> findAllName(){
        return repository.findAll().stream()
                .map(ChronoFunctional::getFilename)
                .collect(Collectors.toList());
    }

    public void uploadFiles(File[] files) {
        for(var file:files){
            uploadFile(file);
        }
    }

    private void uploadFile(File file) {
        try {
            uploadChronoFunctional(convertToMultipartFile(file));
        }catch (Exception exception){
        }
    }

    private MultipartFile convertToMultipartFile(File file) throws IOException {
        var fis = new FileInputStream(file);
        return new MockMultipartFile(file.getName(),fis);
    }

    private void uploadChronoFunctional(MultipartFile file) throws IOException {
        var entity = ChronoFunctional.builder()
                .filename(file.getName())
                .fileData(FileCompressor.zip(file.getBytes()))
                .build();

        var res = repository.save(entity);

        Optional.ofNullable(res)
                .ifPresent(this::printUploadSuccessful);
    }

    private void printUploadSuccessful(ChronoFunctional cf){
        System.out.println("File uploaded successfully: " + cf.getFilename());
    }
}
