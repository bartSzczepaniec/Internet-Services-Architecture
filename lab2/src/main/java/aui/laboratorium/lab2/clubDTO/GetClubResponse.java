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

    private int year_of_foundation;

    private double teams_market_value;

    public static Function<Club, GetClubResponse> entityToDtoMapper() {
        return club -> GetClubResponse.builder()
                .name(club.getName())
                .teams_market_value(club.getTeams_market_value())
                .year_of_foundation(club.getYear_of_foundation())
                .build();
    }

}
