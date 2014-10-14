package fork;
//package com.iflytek.ossp.framework.common.serializable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.alibaba.fastjson.annotation.JSONField;
//
//public class Test {
//
//	public static void main(String[] args) {
//		System.out.println('0');
//		B b = new B();
//		C c = new C();
//		D d = new D();
//		String a = "dd";
//		String json = SerializationInstance.sharedInstance().fastToJson(c, true);
//		System.out.println(json);
//	}
//
//	public static class A {
//		private String str1 = "str1";
//		private int int1 = 100;
//
//		public String getStr1() {
//			return str1;
//		}
//
//		public void setStr1(String str1) {
//			this.str1 = str1;
//		}
//
//		public int getInt1() {
//			return int1;
//		}
//
//		public void setInt1(int int1) {
//			this.int1 = int1;
//		}
//
//	}
//
//	public static class D {
//		@JSONField(name = "test")
//		public int int1 = 200;
//		@JSONField(name = "items")
//		public List<A> list = new ArrayList<>();
//
//		public String[] getItme2() {
//			return new String[] { "item1", "item2" };
//		}
//
//		public D() {
//			for (int i = 0; i < 2; i++) {
//				A a = new A();
//				list.add(a);
//			}
//
//		}
//	}
//
//	public static class B {
//		@JSONField(name = "test")
//		public int int1 = 200;
//		@JSONField(name = "items")
//		public A[] list = new A[2];
//
//		public String[] getItme2() {
//			return new String[] { "item1", "item2" };
//		}
//
//		public B() {
//			for (int i = 0; i < 2; i++) {
//				A a = new A();
//				list[i] = a;
//			}
//
//		}
//	}
//
//	public static class E {
//		@JSONField(implicit = "innerItem")
//		public List<String> innerList = new ArrayList<>();
//
//		public E() {
//			for (int i = 0; i < 2; i++) {
//				innerList.add("inner str " + i);
//			}
//		}
//	}
//
//	public static class C {
//
//		@JSONField(implicit = "item1")
//		public String str1 = "str1";
//
//		@JSONField(implicit = "item2")
//		public String getStr2() {
//			return "str2";
//		}
//
//		// @JSONField(implicit = "items")
//		public String[] list = new String[2];
//		@JSONField(implicit = "items2")
//		public List<String> list2 = new ArrayList<>();
//		// @JSONField(implicit = "items3")
//		public List<A> list3 = new ArrayList<>();
//
//		@JSONField(implicit = "items4")
//		public List<A> getList4() {
//			List<A> arr = new ArrayList<>();
//			for (int i = 0; i < 1; i++) {
//				A a = new A();
//				arr.add(a);
//			}
//			return arr;
//		}
//
//		@JSONField(implicit = "items5")
//		public List<E> list5 = new ArrayList<>();
//
//		public C() {
//			for (int i = 0; i < 2; i++) {
//				list[i] = "STR " + i;
//				list2.add("STR " + i);
//				A a = new A();
//				list3.add(a);
//
//				E e = new E();
//				list5.add(e);
//			}
//
//		}
//	}
//
//}
