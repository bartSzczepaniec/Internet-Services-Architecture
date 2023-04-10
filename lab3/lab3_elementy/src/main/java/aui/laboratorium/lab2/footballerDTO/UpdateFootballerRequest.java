package aui.laboratorium.lab2.footballerDTO;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.Footballer;
import lombok.*;

import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateFootballerRequest {

    private String full_name;

    private int age;

    private double average_rating;

    private String clubname;
    public static BiFunction<Footballer, UpdateFootballerRequest, Footballer> dtoToEntityUpdater(Function<String, Club> clubFunction) {
        return (footballer, request) -> {
            footballer.setFull_name(request.getFull_name());
            footballer.setAge(request.getAge());
            footballer.setAverage_rating(request.getAverage_rating());
            footballer.setClub(clubFunction.apply(request.getClubname()));
            return footballer;
        };
    }

}
