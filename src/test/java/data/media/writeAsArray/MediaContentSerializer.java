package data.media.writeAsArray;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.serializer.FieldSerializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import data.media.Image;
import data.media.Media;
import data.media.MediaContent;

public class MediaContentSerializer implements ObjectSerializer {
    private MediaSerializer mediaSerilaizer = new MediaSerializer(); 
    private ImageSerializer imageSerilaizer = new ImageSerializer(); 

    public void write(JSONSerializer serializer, FieldSerializer parentFieldSerializer, Object object, Object fieldName, Type fieldType) throws IOException {
        MediaContent entity = (MediaContent) object;

        SerializeWriter out = serializer.getWriter();
        out.write('[');
        
        mediaSerilaizer.write(serializer, null, entity.getMedia(), "media", Media.class);
        out.write(',');
        
        out.write('[');
        for (int i = 0; i < entity.getImages().size(); ++i) {
            if (i != 0) {
                out.write(',');
            }
            Image image = entity.getImages().get(i);
            imageSerilaizer.write(serializer, null, image, i, fieldType);
        }
        out.write(']');
        
        out.write(']');
    }

}