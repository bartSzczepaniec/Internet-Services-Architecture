package aui.laboratorium.lab2.service;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.File;
import aui.laboratorium.lab2.entity.Footballer;
import aui.laboratorium.lab2.repository.ClubRepository;
import aui.laboratorium.lab2.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    private FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) { this.fileRepository = fileRepository; }

    public List<File> findAll() {
        return fileRepository.findAll();
    }

    public Optional<File> findById(Long id) { return fileRepository.findById(id); }

    public void saveNew(File file) {
        fileRepository.save(file);
    }
}
