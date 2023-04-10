package aui.laboratorium.lab2.fileDTO;

import aui.laboratorium.lab2.entity.File;
import aui.laboratorium.lab2.entity.Footballer;
import aui.laboratorium.lab2.footballerDTO.GetFootballerResponse;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFileResponse {
    private Long id;

    private String description;

    private String author;

    private String type;

    public static Function<File, GetFileResponse> entityToDtoMapper() {
        return file -> GetFileResponse.builder()
                .id(file.getFile_id())
                .description(file.getDescription())
                .author(file.getAuthor())
                .type(file.getType())
                .build();
    }
}
