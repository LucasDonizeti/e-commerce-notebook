package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Converter
public class ConverterUUIDString implements AttributeConverter<UUID, String> {
        @Override
        public String convertToDatabaseColumn(UUID hash) {
            return hash.toString();
        }

        @Override
        public UUID convertToEntityAttribute(String hash) {
            return UUID.fromString(hash);
        }

}
