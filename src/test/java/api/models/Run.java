package api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Run {
    private String title;
    private String description;
    private int[] cases;
    boolean status;
}
