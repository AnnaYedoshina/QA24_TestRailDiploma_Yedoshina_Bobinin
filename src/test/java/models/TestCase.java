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
public class TestCase {
    private String title;
    @SerializedName("section_id")
    @EqualsAndHashCode.Exclude
    private String section;
    @EqualsAndHashCode.Exclude
    private String template;
    @SerializedName("type_id")
    @EqualsAndHashCode.Exclude
    private String type;
    @SerializedName("priority_id")
    @EqualsAndHashCode.Exclude
    private String priority;
    private String estimate;
    @SerializedName("refs")
    private String references;
    private String automationType;
    private String preconditions;
    private String steps;
    private String expectedResult;

}
