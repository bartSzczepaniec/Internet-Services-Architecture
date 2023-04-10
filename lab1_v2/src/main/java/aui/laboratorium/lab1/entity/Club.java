package aui.laboratorium.lab1.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Club implements Serializable {
    private String name;

    private int yearOfFoundation;

    private double teamsMarketValue;

}
