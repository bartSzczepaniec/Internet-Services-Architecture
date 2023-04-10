package aui.laboratorium.lab2.footballerDTO;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.Footballer;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateFootballerRequest {

    private Long id;

    private String full_name;

    private int age;

    private double average_rating;

    private String clubname;

    public static Function<CreateFootballerRequest, Footballer> dtoToEntityMapper(
            Function<String, Club> clubFunction) {
        return request -> Footballer.builder()
                .full_name(request.getFull_name())
                .age(request.getAge())
                .average_rating(request.getAverage_rating())
                .club(clubFunction.apply(request.getClubname()))
                .build();
    }

}
