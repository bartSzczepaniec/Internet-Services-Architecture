package aui.laboratorium.lab2.fileDTO;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.File;
import aui.laboratorium.lab2.entity.Footballer;
import aui.laboratorium.lab2.footballerDTO.CreateFootballerRequest;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateFileRequest {
    private String author;

    private String description;

    private String type;

    public CreateFileRequest(String author, String description, String type) {
        this.author = author;
        this.description = description;
        this.type = type;
    }

    public static Function<CreateFileRequest, File> dtoToEntityMapper() {
        return request -> File.builder()
                .type(request.getType())
                .author(request.getAuthor())
                .description(request.getDescription())
                .build();
    }
}
