import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args)  {
        Main t = new Main();

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        try {
            t.doSomething();
            t.doSomething2();
        } catch (ClassNotFoundException | MalformedURLException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

        public void doSomething() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, MalformedURLException {
            URL myJar = new File("jar/LibraryA-1.0-SNAPSHOT.jar").toURI().toURL();
            URL myJar3 = new File("jar/v1/LibraryB-1.0-SNAPSHOT.jar").toURI().toURL();
            URLClassLoader child = new URLClassLoader(
                    new URL[] {myJar, myJar3},
                    this.getClass().getClassLoader()
            );
            Class<?> classToLoad = child.loadClass("com.sean.liba.Main");
            child.loadClass("com.sean.lib.Calculator");
            //Class<?> classToLoad = Class.forName("com.sean.liba.Main", true, child);
            //Class<?> classToLoad2 = Class.forName("com.sean.lib.Calculator", true, child);
            Method[] a = classToLoad.getDeclaredMethods();
            Method method = classToLoad.getDeclaredMethod("print");
            Object instance = classToLoad.newInstance();
            method.invoke(instance);
            method.invoke(instance);
        }

    public void doSomething2() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, MalformedURLException {
        URL myJar = new File("jar/LibraryA-1.0-SNAPSHOT.jar").toURI().toURL();
        URL myJar3 = new File("jar/v2/LibraryB-2.0-SNAPSHOT.jar").toURI().toURL();
        URLClassLoader child = new URLClassLoader(
                new URL[] {myJar, myJar3},
                this.getClass().getClassLoader()
        );
        Class<?> classToLoad = child.loadClass("com.sean.liba.Main");
        child.loadClass("com.sean.lib.Calculator");
        //Class<?> classToLoad = Class.forName("com.sean.liba.Main", true, child);
        //Class<?> classToLoad2 = Class.forName("com.sean.lib.Calculator", true, child);
        Method[] a = classToLoad.getDeclaredMethods();
        Method method = classToLoad.getDeclaredMethod("print");
        Object instance = classToLoad.newInstance();
        method.invoke(instance);
    }

}