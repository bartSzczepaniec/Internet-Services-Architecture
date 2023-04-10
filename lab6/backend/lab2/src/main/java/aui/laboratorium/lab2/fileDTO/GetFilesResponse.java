package aui.laboratorium.lab2.fileDTO;

import aui.laboratorium.lab2.entity.File;
import aui.laboratorium.lab2.entity.Footballer;
import aui.laboratorium.lab2.footballerDTO.GetFootballersResponse;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFilesResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class FileResponse {

        private Long id;

        private String description;

    }

    @Singular
    private List<GetFilesResponse.FileResponse> files;

    public static Function<Collection<File>, GetFilesResponse> entityToDtoMapper() {
        return files -> {
            GetFilesResponse.GetFilesResponseBuilder response = GetFilesResponse.builder();
            files.stream()
                    .map(file -> FileResponse.builder()
                            .id(file.getFile_id())
                            .description(file.getDescription())
                            .build())
                    .forEach(response::file);
            return response.build();
        };
    }
}
