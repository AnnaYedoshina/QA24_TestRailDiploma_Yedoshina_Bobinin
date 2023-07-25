package models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder(setterPrefix = "set")
@EqualsAndHashCode
@ToString
@Getter
public class Case {
    @EqualsAndHashCode.Exclude
    int id;
    String title;
    @EqualsAndHashCode.Exclude
    @SerializedName("section_id")
    int sectionId;
    @EqualsAndHashCode.Exclude
    @SerializedName("template_id")
    int templateId;
    @EqualsAndHashCode.Exclude
    @SerializedName("type_id")
    int typeId;
    @EqualsAndHashCode.Exclude
    @SerializedName("priority_id")
    int priorityId;
    @EqualsAndHashCode.Exclude
    String estimate;
}
