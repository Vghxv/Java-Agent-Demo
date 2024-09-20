package myagent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class MyTransformer implements ClassFileTransformer
{
    @Override
    public byte[] transform(ClassLoader loader,
            String className,
            Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals("theapp/Example")) {
            try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.makeClass(new ByteArrayInputStream(classfileBuffer));
                CtMethod getAgeMethod = cc.getDeclaredMethod("getAge");

                // Add code to intercept the return value
                getAgeMethod.insertBefore("{ System.out.println(\"Intercepted getAge()\"); }");
                getAgeMethod.insertAfter("{ System.out.println(\"Return value: \" + $_); }");
                // CtMethod m = cc.getDeclaredMethod("main");
                // m.insertBefore("{ System.out.println(\"Hello, World! 🌍\");}");
                // m.insertAfter("{ System.out.println(\"Bye, Bye! 🖐️🖐️\");}");
                // classfileBuffer = cc.toBytecode();

//                CtMethod myFunction = cc.getDeclaredMethod("myFunction");
//                myFunction.insertBefore("{ System.out.println(\"Calling myFunction with third = \" + $3 ); }");
//                myFunction.insertBefore("{ System.out.println(\"Calling myFunction with second = \" + $2 ); }");
//                myFunction.insertBefore("{ System.out.println(\"Calling myFunction with first = \" + $1 ); }");
//
//                // Insert code after the original method call
//                myFunction.insertAfter("{ System.out.println(\"Result: \" + $_); }");

                classfileBuffer = cc.toBytecode();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RuntimeException e) {
                e.printStackTrace();
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        }
        System.out.println(className);
        return classfileBuffer;
    }
}