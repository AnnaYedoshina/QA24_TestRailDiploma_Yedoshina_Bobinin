package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ProjectResponse {
    private int id;
    private String name;
    private String announcement;
    @SerializedName("show_announcement")
    private boolean isShowedAnnouncement;
    @SerializedName("is_completed")
    private boolean isCompleted;
    @SerializedName("completed_on")
    private String completedOn;
    @SerializedName("suite_mode")
    private int suiteMode;
    @EqualsAndHashCode.Exclude
    private String[] users;
    @EqualsAndHashCode.Exclude
    private String[] groups;

}
