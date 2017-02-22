package com.github.ruediste.privateFieldCodegen;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import sun.misc.Unsafe;

public class AccessorGenerator implements Opcodes {

    @SuppressWarnings({ "restriction", "unchecked" })
    public BiConsumer<Sample, String> generate() {

        try {

            byte[] bb = dump();
            ClassReader cr = new ClassReader(bb);
            Class<?> accessor = getUnsafe().defineAnonymousClass(Sample.class, bb, new Object[cr.getItemCount()]);
            return (BiConsumer<Sample, String>) accessor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, "generated",
                "Ljava/lang/Object;Ljava/util/function/BiConsumer<Lcom/github/ruediste/privateFieldCodegen/Sample;Ljava/lang/String;>;",
                "java/lang/Object", new String[] { "java/util/function/BiConsumer" });

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(5, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lcom/github/ruediste/privateFieldCodegen/SampleSetter;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "accept",
                    "(Lcom/github/ruediste/privateFieldCodegen/Sample;Ljava/lang/String;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(8, l0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitFieldInsn(PUTFIELD, "com/github/ruediste/privateFieldCodegen/Sample", "message",
                    "Ljava/lang/String;");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(10, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "Lcom/github/ruediste/privateFieldCodegen/SampleSetter;", null, l0, l2, 0);
            mv.visitLocalVariable("sample", "Lcom/github/ruediste/privateFieldCodegen/Sample;", null, l0, l2, 1);
            mv.visitLocalVariable("message", "Ljava/lang/String;", null, l0, l2, 2);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "accept",
                    "(Ljava/lang/Object;Ljava/lang/Object;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(1, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(CHECKCAST, "com/github/ruediste/privateFieldCodegen/Sample");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitTypeInsn(CHECKCAST, "java/lang/String");
            mv.visitMethodInsn(INVOKEVIRTUAL, "generated", "accept",
                    "(Lcom/github/ruediste/privateFieldCodegen/Sample;Ljava/lang/String;)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 3);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }

    @SuppressWarnings("restriction")
    private sun.misc.Unsafe getUnsafe() {
        try {
            Field unsafeField = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            sun.misc.Unsafe unsafe = (Unsafe) unsafeField.get(null);
            return unsafe;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
