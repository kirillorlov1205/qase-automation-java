package api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Plan {
    private String title;
    private String description;
    private int[] cases;
}
