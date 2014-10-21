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
		A a = new A();
		System.out.println(a);
		String json = JSON.toJSONString(a, false);
		System.out.println(json);

		A newA = JSON.parseObject(json, A.class);
		System.out.println(newA);

	}

	@JSONType(asm = true, name = "root")
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

		@Override
		public String toString() {
			return "A [str=" + str + ", list=" + list + "]";
		}

	}

	@JSONType(asm = true)
	public static class A1 {
		public String str = "str";

		@Override
		public String toString() {
			return "A1 [str=" + str + "]";
		}
	}

}
