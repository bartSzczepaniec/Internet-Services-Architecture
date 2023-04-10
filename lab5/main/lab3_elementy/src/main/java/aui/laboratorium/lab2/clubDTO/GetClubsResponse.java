package aui.laboratorium.lab2.clubDTO;

import aui.laboratorium.lab2.entity.Club;
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
public class GetClubsResponse {
    @Singular
    private List<String> clubs;

    public static Function<Collection<Club>, GetClubsResponse> entityToDtoMapper() {
        return characters -> {
            GetClubsResponseBuilder response = GetClubsResponse.builder();
            characters.stream()
                    .map(Club::getName)
                    .forEach(response::club);
            return response.build();
        };
    }


}
