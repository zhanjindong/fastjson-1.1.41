package com.alibaba.json.annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONField_implicit_0 extends TestCase {

	public static void main(String[] args) {

		Test0_1 data = getData1();
		String text = data.toString();
		String json = JSON.toJSONString(data, SerializerFeature.PrettyFormat);
		System.out.println(json);
		Test0_1 newData = JSON.parseObject(json, Test0_1.class, Feature.IgnoreNotMatch);
		String newText = newData.toString();
		System.out.println(text.equals(newText));
	}

	public void test_0() {
		Test0 test = getData();
		String text = test.toString();
		String json = JSON.toJSONString(test, SerializerFeature.PrettyFormat);
		Test0 newTest = JSON.parseObject(json, Test0.class, Feature.IgnoreNotMatch);
		String newText = newTest.toString();
		Assert.assertEquals(newText, text);
	}

	public void test_1() {
		Test0_1 data = getData1();
		String json = JSON.toJSONString(data, SerializerFeature.PrettyFormat);
		System.out.println(json);
	}

	public static Test0 getData() {
		Test0 test = new Test0();
		test.setInt1(1);
		test.setLong1(2);
		test.setStr1("str 1");

		List<String> strArr = new ArrayList<String>();
		for (int i = 0; i < 2; i++) {
			strArr.add("str " + i + "in strARR");
		}
		test.setStrArr(strArr);

		List<Item> items = new ArrayList<JSONField_implicit_0.Item>();
		for (int i = 0; i < 20; i++) {
			Item item = new Item();
			item.setItem_int1(101);
			item.setItem_str1("item_str1 " + i);

			List<InnerItme> innerItmes = new ArrayList<JSONField_implicit_0.InnerItme>();
			for (int j = 0; j < 10; j++) {
				InnerItme innerItme = new InnerItme();
				innerItme.setInnerItme_int1(10001);
				innerItme.setInnerItme_str1("innerItme_str1 " + i);
				innerItmes.add(innerItme);
			}
			item.setInnerItmes(innerItmes);
			items.add(item);
		}
		test.setItems(items);

		return test;
	}

	public static Test0_1 getData1() {
		Test0_1 data = new Test0_1();

		Item[] items = new Item[2];
		for (int i = 0; i < items.length; i++) {
			Item item = new Item();
			item.setItem_int1(i);
			item.setItem_str1("item_str1 " + i);
			List<InnerItme> innerItmes = new ArrayList<JSONField_implicit_0.InnerItme>();
			for (int j = 0; j < 2; j++) {
				InnerItme innerItme = new InnerItme();
				innerItme.setInnerItme_int1(10001);
				innerItme.setInnerItme_str1("innerItme_str1 " + i);
				innerItmes.add(innerItme);
			}
			item.setInnerItmes(innerItmes);
			items[i] = item;

		}
		data.setItemArr(items);

		return data;
	}

	public static class Test0_1 {
		@JSONField(implicit = true)
		private Item[] itemArr = new Item[2];

		public Item[] getItemArr() {
			return itemArr;
		}

		public void setItemArr(Item[] itemArr) {
			this.itemArr = itemArr;
		}

		@Override
		public String toString() {
			return "Test0_1 [itemArr=" + Arrays.toString(itemArr) + "]";
		}
	}

	public static class Test0 {
		private String str1;
		private int int1;
		private long long1;

		@JSONField(name = "strList", implicit = true)
		private List<String> strArr = new ArrayList<String>();
		@JSONField(name = "item", implicit = true)
		private List<Item> items = new ArrayList<JSONField_implicit_0.Item>();

		public String getStr1() {
			return str1;
		}

		public void setStr1(String str1) {
			this.str1 = str1;
		}

		public int getInt1() {
			return int1;
		}

		public void setInt1(int int1) {
			this.int1 = int1;
		}

		public long getLong1() {
			return long1;
		}

		public void setLong1(long long1) {
			this.long1 = long1;
		}

		public List<String> getStrArr() {
			return strArr;
		}

		public void setStrArr(List<String> strArr) {
			this.strArr = strArr;
		}

		public List<Item> getItems() {
			return items;
		}

		public void setItems(List<Item> items) {
			this.items = items;
		}

		@Override
		public String toString() {
			return "Test [str1=" + str1 + ", int1=" + int1 + ", long1=" + long1 + ", strArr=" + strArr + ", items="
					+ items + "]";
		}

	}

	public static class Item {
		private String item_str1;
		private int item_int1;

		@JSONField(implicit = false)
		private List<InnerItme> innerItmes = new ArrayList<JSONField_implicit_0.InnerItme>();

		public String getItem_str1() {
			return item_str1;
		}

		public void setItem_str1(String item_str1) {
			this.item_str1 = item_str1;
		}

		public int getItem_int1() {
			return item_int1;
		}

		public void setItem_int1(int item_int1) {
			this.item_int1 = item_int1;
		}

		public List<InnerItme> getInnerItmes() {
			return innerItmes;
		}

		public void setInnerItmes(List<InnerItme> innerItmes) {
			this.innerItmes = innerItmes;
		}

		@Override
		public String toString() {
			return "Item [item_str1=" + item_str1 + ", item_int1=" + item_int1 + ", innerItmes=" + innerItmes + "]";
		}

	}

	public static class InnerItme {
		private String innerItme_str1;
		private int innerItme_int1;

		public String getInnerItme_str1() {
			return innerItme_str1;
		}

		public void setInnerItme_str1(String innerItme_str1) {
			this.innerItme_str1 = innerItme_str1;
		}

		public int getInnerItme_int1() {
			return innerItme_int1;
		}

		public void setInnerItme_int1(int innerItme_int1) {
			this.innerItme_int1 = innerItme_int1;
		}

		@Override
		public String toString() {
			return "InnerItme [innerItme_str1=" + innerItme_str1 + ", innerItme_int1=" + innerItme_int1 + "]";
		}

	}

}
