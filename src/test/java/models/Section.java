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
public class Section {
    int id;
    @SerializedName("suite_id")
    int suiteId;
    String name;
    String description;
    @SerializedName("parent_id")
    String parentId;
    @SerializedName("display_order")
    int displayOrder;
    int depth;
}
