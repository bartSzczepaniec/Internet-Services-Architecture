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

    public static Function<CreateClubRequest, Club> dtoToEntityMapper() {
        return request -> Club.builder()
                .name(request.getName())
                .build();
    }

}
