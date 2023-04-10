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

    private int year_of_foundation;

    private double teams_market_value;

    public static BiFunction<Club, UpdateClubRequest, Club> dtoToEntityUpdater() {
        return (club, request) -> {
            club.setYear_of_foundation(request.getYear_of_foundation());
            club.setTeams_market_value(request.getTeams_market_value());
            return club;
        };
    }

}
