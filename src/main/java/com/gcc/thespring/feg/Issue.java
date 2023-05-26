package com.gcc.thespring.feg;

import lombok.Data;

import java.util.List;

@Data
public class Issue {
    private String title;
    private String body;
    private List<String> assignees;
    private int milestone;
    private List<String> labels;
}
