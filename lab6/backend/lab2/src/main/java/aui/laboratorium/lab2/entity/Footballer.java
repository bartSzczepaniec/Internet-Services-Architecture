package aui.laboratorium.lab2.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode()
@Entity
@Table(name = "footballers")
public class Footballer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String full_name;

    private int age;

    private double average_rating;

    @ManyToOne
    @JoinColumn(name ="club")
    private Club club;

}
