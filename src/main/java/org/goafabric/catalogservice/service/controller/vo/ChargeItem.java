package org.goafabric.catalogservice.service.controller.vo;

public record ChargeItem (
    String id,
    String code,
    String display,
    Double price
) {}
