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
    int id;
    String title;
    @SerializedName("section_id")
    int sectionId;
    @SerializedName("template_id")
    int templateId;
    @SerializedName("type_id")
    int typeId;
    @SerializedName("priority_id")
    int priorityId;
    String estimate;
}
