import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args)  {
        Main t = new Main();

        try {
            t.doSomething();
            t.doSomething2();
        } catch (ClassNotFoundException | MalformedURLException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void doSomething() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, MalformedURLException {
        System.out.println("========== Classloader 1 ===========");
        URL myJar = new File("Application/jar/LibraryA-1.0-SNAPSHOT.jar").toURI().toURL();
        URL myJar2 = new File("Application/jar/v1/LibraryB-1.0-SNAPSHOT.jar").toURI().toURL();
        URLClassLoader child = new URLClassLoader(
                new URL[] {myJar, myJar2},
                this.getClass().getClassLoader()
        );
        Class<?> classToLoad = Class.forName("com.sean.liba.Main", true, child);
        Method method = classToLoad.getDeclaredMethod("print");
        Object instance = classToLoad.newInstance();
        method.invoke(instance);
        method.invoke(instance);
    }

    public void doSomething2() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, MalformedURLException {
        System.out.println("========== Classloader 2 ===========");
        URL myJar = new File("Application/jar/LibraryA-2.0-SNAPSHOT.jar").toURI().toURL();
        URL myJar2 = new File("Application/jar/v1/LibraryB-1.0-SNAPSHOT.jar").toURI().toURL();
        URLClassLoader child = new URLClassLoader(
                new URL[] {myJar, myJar2},
                this.getClass().getClassLoader()
        );
        Class<?> classToLoad = child.loadClass("com.sean.liba.Main");
        Method method = classToLoad.getDeclaredMethod("print2");
        Object instance = classToLoad.newInstance();
        method.invoke(instance);
    }

}