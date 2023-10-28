package api.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Case {
    private String title;
    private int type;
    private int severity;
    private int priority;
    private String preconditions;
    private String postconditions;
}
