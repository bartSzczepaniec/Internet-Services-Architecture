package aui.laboratorium.lab2.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "clubs")
public class Club implements Serializable {
    @Id
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "club")
    @ToString.Exclude
    private List<Footballer> footballers;

}
