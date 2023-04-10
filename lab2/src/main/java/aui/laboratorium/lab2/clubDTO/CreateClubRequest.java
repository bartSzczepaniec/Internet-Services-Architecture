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
public class CreateClubRequest {
    private String name;

    private int year_of_foundation;

    private double teams_market_value;

    public static Function<CreateClubRequest, Club> dtoToEntityMapper() {
        return request -> Club.builder()
                .name(request.getName())
                .year_of_foundation(request.getYear_of_foundation())
                .teams_market_value(request.getTeams_market_value())
                .build();
    }

}
