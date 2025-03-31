package com.ecommerce.electronicshop.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageNameValidator  implements ConstraintValidator<ImageNameValid,String> {

private Logger logger = LoggerFactory.getLogger(ImageNameValidator.class);

    @Override
public boolean isValid(String value, ConstraintValidatorContext context) {

        logger.info("Message from isValid : {}", value);

//  Logic
        if (value.isBlank()) {
            return false;
        } else {
            return true;
        }

    }


}