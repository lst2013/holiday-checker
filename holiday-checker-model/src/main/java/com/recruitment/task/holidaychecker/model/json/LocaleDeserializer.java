package com.recruitment.task.holidaychecker.model.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Locale;

public class LocaleDeserializer extends JsonDeserializer<Locale> {

    public Locale deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getValueAsString();

        if (StringUtils.isEmpty(value)) {
            return null;
        }

        return new Locale(value);
    }
}