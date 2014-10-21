package debug;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.Opcodes;

public class ASMTest implements Opcodes {

	public static void main(String[] args) throws IOException {
		// 生成一个类只需要ClassWriter组件即可
		ClassWriter cw = new ClassWriter();
		// 通过visit方法确定类的头部信息
		cw.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, "com/asm3/Comparable", null, new String[] { "com/asm3/Mesurable" });
		// 定义类的属性
		cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "LESS", "I").visitEnd();
		cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "EQUAL", "I").visitEnd();
		cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "GREATER", "I").visitEnd();
		// 定义类的方法
		cw.visitMethod(Opcodes.ACC_PUBLIC, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();

		MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mw.visitVarInsn(ALOAD, 0);
		mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		// 将cw转换成字节数组写到文件里面去
		byte[] data = cw.toByteArray();
		File file = new File("D://temp/asm/Comparable.class");
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(data);
		fout.close();
	}

}
