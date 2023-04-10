package aui.laboratorium.lab2.event.dto;

import aui.laboratorium.lab2.entity.Club;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostClubRequest {

    private String name;

    public static Function<Club, PostClubRequest> entityToDtoMapper() {
        return entity -> PostClubRequest.builder()
                .name(entity.getName())
                .build();
    }

}
