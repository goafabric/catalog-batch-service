package org.goafabric.catalogservice.service.crossfunctional;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DurationLog {
}