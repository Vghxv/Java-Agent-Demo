# Java Agent Demo
## Goal
simple Hello World example, modify the output string 'Hello World! 👀' to 'Bye, Bye! 🖐️' using javassist library.

## Prerequisites
openjdk : 11.0.11

## Demo
```bash
# build helloworld.jar
cd helloworldapp
make jar
```
- prints 'Hello World! 👀' to console
```bash
# run helloworld.jar
cd agent
make
```
- prints 'Bye, Bye! 🖐️' to console
## Structure
```
├── README.md
├── agent
│   ├── Makefile
│   ├── lib
│   │   └── javassist.jar
│   └── src
│       ├── manifest.mf
│       └── myagent
│           ├── MyAgent.java
│           └── MyTransformer.java
└── helloworldapp
    ├── Makefile
    └── src
        ├── HelloWorld.java
        └── manifest.mf
```

## Instructions
#### Three jar files are needed for demo
- javassist.jar
- helloworld.jar
- myagent.jarlibrary.
### javassist.jar
```bash
wget https://github.com/jboss-javassist/javassist/releases/download/rel_3_29_2_ga/javassist.jar
```
### helloworld.jar
- simple java application, print 'Hello World!' to console
- specify the 'Main-class' header in manifest.mf, in provided example, it is `theapp.HelloWorld`
    
### myagent.jar
- Consists of a entry point class and a transformer class
- Create `MyTransformer` class implements the interface [`java.lang.instrument.ClassFileTransformer`](https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/lang/instrument/ClassFileTransformer.html)
- `MyTransformer` overrides the method `transform` to modify the bytecode of HelloWorld.class
- Create `ClassPool` object to load the class file
- Read the class file into a `CtClass` object using `ClassPool` and a `ByteArrayInputStream` created from the `classfileBuffer`
- Obtain the `CtMethod` representing the `main` method from the `CtClass` object
- Convert the modified `CtClass` back to bytecode using `cc.toBytecode()`
- Update the `classfileBuffer` with the modified bytecode
- specify the 'Premain-Class' header in manifest.mf, in provided example, it is `myagent.MyAgent`

