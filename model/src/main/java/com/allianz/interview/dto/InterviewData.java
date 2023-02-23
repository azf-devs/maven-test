package com.allianz.interview.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class InterviewData {
    private Integer id;

    private String label;
    private LocalDate date;


}
