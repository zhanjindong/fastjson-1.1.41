package debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

public class Debug {

	public static void main(String[] args) {
		String json = JSON.toJSONString(new A(), true);
		System.out.println(json);

		// json = JSON.toJSONString(new A1());
		// System.out.println(json);
		//
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("key", "value");
		// json = JSON.toJSONString(map);
		// System.out.println(json);
	}

	@JSONType(asm = false)
	public static class A {
		public String str = "str";

		@JSONField(implicit = true)
		public List<A1> list = new ArrayList<Debug.A1>();

		public A() {
			for (int i = 0; i < 2; i++) {
				A1 a = new A1();
				list.add(a);
			}
		}

		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}

		public List<A1> getList() {
			return list;
		}

		public void setList(List<A1> list) {
			this.list = list;
		}

		public String getStr3() {
			return "stre";
		}
	}

	@JSONType(asm = true)
	public static class A1 {
		public String str = "str";
	}

}
