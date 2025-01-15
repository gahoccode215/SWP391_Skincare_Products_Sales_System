package com.swp391.skincare_products_sales_system.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) return null;
        switch (gender) {
            case MALE:
                return "male";
            case FEMALE:
                return "female";
            case OTHER:
                return "other";
            default:
                throw new IllegalArgumentException("Unknown gender: " + gender);
        }
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        switch (dbData) {
            case "male":
                return Gender.MALE;
            case "female":
                return Gender.FEMALE;
            case "other":
                return Gender.OTHER;
            default:
                throw new IllegalArgumentException("Unknown gender value: " + dbData);
        }
    }
}
