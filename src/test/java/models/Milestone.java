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
public class Milestone {
    private final String name;
    @SerializedName("refs")
    private final String references;
    private final String description;
}