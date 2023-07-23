package models;

import lombok.Data;

import java.util.List;

@Data
public class ProjectsResponse {
    int size;
    List<Project> projects;
}
