package com.sellami.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.time.LocalDate;
import java.util.Date;

@EntityScan
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Label {

    private Long id;
    private String label;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
}
