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
@EqualsAndHashCode()
public class Footballer implements Serializable {

    private long id;

    private String fullName;

    private int age;

    private double averageRating;

    private Club club;

}
