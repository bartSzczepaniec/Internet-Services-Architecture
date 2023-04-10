package aui.laboratorium.lab2.footballerDTO;

import aui.laboratorium.lab2.clubDTO.GetClubsResponse;
import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.Footballer;
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

public class GetFootballersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class FootballerResponsing {


        private Long id;

        private String full_name;

    }

    @Singular
    private List<FootballerResponsing> footballers;

    public static Function<Collection<aui.laboratorium.lab2.entity.Footballer>, GetFootballersResponse> entityToDtoMapper() {
        return footballers -> {
            GetFootballersResponse.GetFootballersResponseBuilder response = GetFootballersResponse.builder();
            footballers.stream()
                    .map(footballer -> FootballerResponsing.builder()
                            .id(footballer.getId())
                            .full_name(footballer.getFull_name())
                            .build())
                    .forEach(response::footballer);
            return response.build();
        };
    }
}
