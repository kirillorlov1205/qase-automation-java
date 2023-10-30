package api.models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Milestone {
    private String title;
    private String description;
    private String status;
    @SerializedName("due_date")
    private int dueDate;
}
