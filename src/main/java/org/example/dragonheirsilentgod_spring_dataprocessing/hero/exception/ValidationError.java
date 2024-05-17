package org.example.dragonheirsilentgod_spring_dataprocessing.hero.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
@Data
@Getter
@AllArgsConstructor
public class ValidationError {

    private String field;

    private String message;

}
