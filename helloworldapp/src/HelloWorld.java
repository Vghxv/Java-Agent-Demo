package theapp;
class Example{
    private final int age;
    public Example(int age){
        this.age = age;
    }

    public int getAge(){
        return age;
    }
}
class HelloWorld {
    
    public static String myFunction(int x, int y, String z) {
        return String.valueOf(x + y) + "_" + z;
    }

    public static void main(String []args) {
        System.out.println("Hello World! 👀");
        Example example = new Example(25);
        int age = example.getAge();
        System.out.println("Hi my Age is " + age);
    }
}
