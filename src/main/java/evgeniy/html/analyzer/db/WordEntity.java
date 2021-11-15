package evgeniy.html.analyzer.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity @Table(name = "words")
@NonNull
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class WordEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    private String datasource;

    private String word;

    private int count;

    private LocalDateTime createdAt;

}
