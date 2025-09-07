package com.sadbmo.adapters;

import java.io.IOException;
import java.io.InputStream;

public abstract class JsonMapperAdapter {
   public abstract <T>T readValue(InputStream inputStream, Class<T> valueType) throws IOException;
}
