package domain;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class SportEvent {
    private Long id;
    private String name;
    private Timestamp kickoff;
    private League league;
    private List<Market> markets;
}
