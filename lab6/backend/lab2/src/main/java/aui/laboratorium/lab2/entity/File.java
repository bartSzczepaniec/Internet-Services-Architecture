package aui.laboratorium.lab2.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode()
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long file_id;

    private String description;

    private String author;

    private String type;

    //@Lob
    //@Basic(fetch = FetchType.LAZY)
    //@ToString.Exclude
    //private byte[] stored_file;
}
