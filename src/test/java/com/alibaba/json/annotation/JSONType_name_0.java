package com.alibaba.json.annotation;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

public class JSONType_name_0 extends TestCase {

	public static void main(String[] args) {
		Test_0 data = getData();
		System.out.println(data);
		String json = JSON.toJSONString(data);
		System.out.println(json);
		Test_0 newData = JSON.parseObject(json, Test_0.class);
		String newText = newData.toString();
		System.out.println(newText);

	}

	public void test_0() {
		Test_0 data = getData();
		String text = data.toString();
		String json = JSON.toJSONString(data);
		Test_0 newData = JSON.parseObject(json, Test_0.class);
		String newText = newData.toString();
		assertEquals(text, newText);
	}

	public static Test_0 getData() {
		Test_0 data = new Test_0();

		data.setStr1("str 1 test");
		List<Item> items = new ArrayList<JSONType_name_0.Item>();
		for (int i = 0; i < 5; i++) {
			Item item = new Item();
			item.setInt1(i);
			items.add(item);
		}
		data.setItems(items);

		return data;
	}

	@JSONType(name = "result")
	public static class Test_0 {
		private String str1;

		@JSONField(name = "item", implicit = true)
		private List<Item> items = new ArrayList<JSONType_name_0.Item>();

		public String getStr1() {
			return str1;
		}

		public void setStr1(String str1) {
			this.str1 = str1;
		}

		public List<Item> getItems() {
			return items;
		}

		public void setItems(List<Item> items) {
			this.items = items;
		}

		@Override
		public String toString() {
			return "Test_0 [str1=" + str1 + ", items=" + items + "]";
		}
	}

	// 不是最外层JSONType的name会被忽略。
	@JSONType(name = "items")
	public static class Item {
		private int int1 = -1;

		public int getInt1() {
			return int1;
		}

		public void setInt1(int int1) {
			this.int1 = int1;
		}

		@Override
		public String toString() {
			return "Item [int1=" + int1 + "]";
		}

	}

}
