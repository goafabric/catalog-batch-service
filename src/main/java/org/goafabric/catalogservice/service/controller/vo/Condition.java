package org.goafabric.catalogservice.service.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Condition {
    private String id;

    private String code;
    private String display;
    private String shortname;
}
