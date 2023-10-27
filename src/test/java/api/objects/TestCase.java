package api.objects;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCase {

    private String preconditions;
    @SerializedName("postconditions")
    private String postConditions;
    private String title;
    private int severity;
    private int priority;
}
