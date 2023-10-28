package api.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Suite {
    private String title;
    private String description;
    private String preconditions;
}
