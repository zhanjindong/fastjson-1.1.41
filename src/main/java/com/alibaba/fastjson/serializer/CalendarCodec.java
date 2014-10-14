package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.DateDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

public class CalendarCodec implements ObjectSerializer, ObjectDeserializer {

    public final static CalendarCodec instance = new CalendarCodec();

    public void write(JSONSerializer serializer, FieldSerializer parentFieldSerializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Calendar calendar = (Calendar) object;
        Date date = calendar.getTime();
        serializer.write(date);
    }

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, FieldDeserializer fieldDeserializer, Type type, Object fieldName) {
        Date date = DateDeserializer.instance.deserialze(parser, null, type, fieldName);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        return (T) calendar;
    }

    public int getFastMatchToken() {
        return JSONToken.LITERAL_INT;
    }
}
