package com.alibaba.json.bvt.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Assert;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONReaderScanner;

public class JSONReaderScannerTest__entity_int extends TestCase {

    public void test_scanInt() throws Exception {
        StringBuffer buf = new StringBuffer();
        buf.append('[');
        for (int i = 0; i < 754; ++i) {
            if (i != 0) {
                buf.append(',');
            }
            buf.append("{\"id\":" + i + "}");
        }
        buf.append(']');

        Reader reader = new StringReader(buf.toString());

        JSONReaderScanner scanner = new JSONReaderScanner(reader);

        DefaultJSONParser parser = new DefaultJSONParser(scanner);
        List<VO> array = parser.parseArray(VO.class);
        for (int i = 0; i < array.size(); ++i) {
            Assert.assertEquals(i, array.get(i).getId());
        }
    }

    public static class VO {

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }
    
    public static void main(String[] args){
    	StringBuffer buf = new StringBuffer();
        buf.append('[');
        for (int i = 0; i < 754; ++i) {
            if (i != 0) {
                buf.append(',');
            }
            buf.append("{\"id\":" + i + "}");
        }
        buf.append(']');
        System.out.println(buf.toString());

		Reader reader = new StringReader(buf.toString());

		JSONReaderScanner scanner = new JSONReaderScanner(reader);

		DefaultJSONParser parser = new DefaultJSONParser(scanner);
		List<VO> array = parser.parseArray(VO.class);
		for (int i = 0; i < array.size(); ++i) {
			Assert.assertEquals(i, array.get(i).getId());
		}
    }
}
