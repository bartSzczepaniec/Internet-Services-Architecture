package aui.laboratorium.lab2.restControllers;

import aui.laboratorium.lab2.clubDTO.CreateClubRequest;
import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.File;
import aui.laboratorium.lab2.fileDTO.CreateFileRequest;
import aui.laboratorium.lab2.fileDTO.GetFileResponse;
import aui.laboratorium.lab2.fileDTO.GetFilesResponse;
import aui.laboratorium.lab2.service.FileService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/files")
public class FileController {
    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<GetFilesResponse> getClubs(){
        return ResponseEntity.ok(GetFilesResponse.entityToDtoMapper().apply(fileService.findAll()));
    }
    @GetMapping("{fileid}/info")
    public ResponseEntity<GetFileResponse> getFile(@PathVariable("fileid") Long id) {
        return fileService.findById(id)
                .map(value -> ResponseEntity
                        .ok(GetFileResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }
    @GetMapping(value = "{fileid}")
    public ResponseEntity<byte[]> getFileDownloaded(@PathVariable("fileid") Long id) {
        return fileService.findById(id)
                .map(value -> ResponseEntity.ok()
                        .contentType(MediaType.valueOf("application/octet-stream"))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + value.getFile_id() + "." + value.getType() + "\"")
                        .body(getResourceAsByteArray("/uploads/" + value.getFile_id().toString() + "." + value.getType()) ))  // value.getStored_file()
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // /Users/bartlomiejszczeaniec/Desktop/aui/lab6/lab2/src/main/resources/uploads/ - path to my directory

    @SneakyThrows
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createFile(@RequestParam("stored_file")MultipartFile stored_file, @RequestParam("author") String author, @RequestParam("description") String description, @RequestParam("type") String type, UriComponentsBuilder builder) {
        String file_name = stored_file.getOriginalFilename();
        String[] parts = file_name.split("\\.");
        String file_type = parts[parts.length-1];

        CreateFileRequest request = new CreateFileRequest(author, description, type);

        File file = CreateFileRequest.dtoToEntityMapper().apply(request);
        fileService.saveNew(file);
        stored_file.transferTo( new java.io.File("//uploads/" + file.getFile_id().toString() + "." + file_type));

        //return ResponseEntity.accepted().build();
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "files", "{fileid}")
                        .buildAndExpand(file.getFile_id()).toUri())
                .build();
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = Files.newInputStream(Paths.get(name))) {
            //System.out.println(name);
            return is.readAllBytes();
        }
    }
}
