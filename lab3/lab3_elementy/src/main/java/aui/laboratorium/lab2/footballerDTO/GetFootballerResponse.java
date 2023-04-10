package aui.laboratorium.lab2.footballerDTO;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.Footballer;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFootballerResponse {

    private Long id;

    private String full_name;

    private int age;

    private double average_rating;

    private String club;

    public static Function<Footballer, GetFootballerResponse> entityToDtoMapper(Club club) {
        if(club != null) {
            return footballer -> GetFootballerResponse.builder()
                    .id(footballer.getId())
                    .full_name(footballer.getFull_name())
                    .age(footballer.getAge())
                    .average_rating(footballer.getAverage_rating())
                    .club(footballer.getClub().getName())
                    .build();
        }
        else {
            return footballer -> GetFootballerResponse.builder()
                    .id(footballer.getId())
                    .full_name(footballer.getFull_name())
                    .age(footballer.getAge())
                    .average_rating(footballer.getAverage_rating())
                    .club("null")
                    .build();
        }
    }
}
