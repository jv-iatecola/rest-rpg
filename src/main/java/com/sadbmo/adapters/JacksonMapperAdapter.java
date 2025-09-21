package com.sadbmo.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JacksonMapperAdapter extends JsonMapperAdapter {
    private final ObjectMapper mapper;

    public JacksonMapperAdapter() {
       this.mapper = new ObjectMapper();
    }

    @Override
    public <T> T readValue(InputStream inputStream, Class<T> valueType) throws IOException {
        return this.mapper.readValue(inputStream, valueType);
    }
}
