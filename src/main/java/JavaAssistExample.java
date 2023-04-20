import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javassist.*;

public class JavaAssistExample {


    public static void main(String[] args) {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ct = classPool.makeClass("FakeClass");
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        try {
            CtField field;

            field = new CtField(classPool.get(Integer.class.getName()), "value", ct);
            field.setModifiers(Modifier.PUBLIC);
            ct.addMethod(CtNewMethod.setter("setValue", field));
            ct.addMethod(CtNewMethod.getter("getValue", field));
            ct.addField(field);

            field = new CtField(classPool.get(String.class.getName()), "name", ct);
            field.setModifiers(Modifier.PUBLIC);
            ct.addMethod(CtNewMethod.setter("setName", field));
            ct.addMethod(CtNewMethod.getter("getName", field));
            ct.addField(field);

            CtClass[] constrParam = new CtClass[2];
            constrParam[0] = classPool.get(String.class.getName());
            constrParam[1] = classPool.get(Integer.class.getName());
            CtConstructor constructor = new CtConstructor(constrParam, ct);
            constructor.setModifiers(Modifier.PUBLIC);
            constructor.setBody("{this.name=$1; this.value=$2;}");
            ct.addConstructor(constructor);

            CtClass[] methodParam = new CtClass[2];
            methodParam[0] = classPool.get(String.class.getName());
            methodParam[1] = classPool.get(Integer.class.getName());

//		    CtMethod m = CtNewMethod
//					.make("public void pp1() { System.out.println(\"Hello     World\");}",
//							ct);
//			ct.addMethod(m);

            CtMethod method = new CtMethod(CtClass.booleanType, "printall", methodParam, ct);
            method.setModifiers(Modifier.PUBLIC);
//		    method.setBody("{System.out.println(\"name:\" + this.name + \",value:\" + this.value);  return true;}");
            method.setBody("{System.out.println(\"name:\" + $1 + \",value:\" + $2);  return true;}");
            ct.addMethod(method);

            System.out.println("--1" + df.format(new Date()));
            ct.writeFile();
            System.out.println("--2" + df.format(new Date()));
            Class<?> clazz = ct.toClass();
            System.out.println("--3" + df.format(new Date()));
            Object o = clazz.getConstructor(new Class[]{String.class,Integer.class}).newInstance("myname", 123);
            System.out.println("--4" + df.format(new Date()));
            Method mm = o.getClass().getMethod("printall", new Class[] {String.class, Integer.class});
            System.out.println("--5" + df.format(new Date()));
            mm.invoke(o, "fff", 34);
            System.out.println("--6" + df.format(new Date()));
        } catch (CannotCompileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}