package com.swp391.skincare_products_sales_system.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.constraints.Pattern;

public class EnumPatternValidator implements ConstraintValidator<EnumPattern, Enum<?>> {
    private Pattern pattern;
}
