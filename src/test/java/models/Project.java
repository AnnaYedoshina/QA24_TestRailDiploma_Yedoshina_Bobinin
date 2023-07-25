package models;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@EqualsAndHashCode
@ToString
@Getter
@Builder(setterPrefix = "set")
public class Project {
    @EqualsAndHashCode.Exclude
    private int id;
    private String name;

    private String announcement;
    @SerializedName("show_announcement")
    private boolean showAnnouncement;
    @EqualsAndHashCode.Exclude
    @SerializedName("is_completed")
    private boolean isCompleted;
    @EqualsAndHashCode.Exclude
    @SerializedName("completed_on")
    private String completedOn;
    @SerializedName("suite_mode")
    private int suiteMode;
    @EqualsAndHashCode.Exclude
    @SerializedName("default_role_id")
    private int defaultRoleId;
    @EqualsAndHashCode.Exclude
    private String url;
    @EqualsAndHashCode.Exclude
    private String[] users;
    @EqualsAndHashCode.Exclude
    private String[] groups;
}



