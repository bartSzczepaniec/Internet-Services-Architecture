package aui.laboratorium.lab2.clubDTO;

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
public class GetClubResponse {
    private String name;

    public static Function<Club, GetClubResponse> entityToDtoMapper() {
        return club -> GetClubResponse.builder()
                .name(club.getName())
                .build();
    }

}
