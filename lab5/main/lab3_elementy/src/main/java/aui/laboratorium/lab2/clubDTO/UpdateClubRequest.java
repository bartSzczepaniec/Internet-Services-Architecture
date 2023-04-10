package aui.laboratorium.lab2.clubDTO;

import aui.laboratorium.lab2.entity.Club;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateClubRequest {

    private String name;

    public static BiFunction<Club, UpdateClubRequest, Club> dtoToEntityUpdater() {
        return (club, request) -> {
            return club;
        };
    }

}
