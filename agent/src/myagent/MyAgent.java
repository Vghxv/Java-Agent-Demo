package myagent;

import java.lang.instrument.Instrumentation;

public class MyAgent {
    public static void premain(String args, Instrumentation instrumentation) {
        MyTransformer transformer = new MyTransformer();
        instrumentation.addTransformer(transformer);
    }
}