package com.blackoon.lr.jdk.serial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化给我们提供了一种技术，用于保存对象的变量。以便于传输。虽然也可以使用别的一些方法实现同样的功能，但是java给我们提供的方法使用起来是非常方便的
 * 
 *总结：Java 序列化技术可以使你将一个对象的状态写入一个Byte 流里（系列化），并且可以从其它地方把该Byte 流里的数据读出来（反序列化）。
 *用途 1.想把的内存中的对象状态保存到一个文件中或者数据库中时候 
 *    2.想把对象通过网络进行传播的时候
 *    说罢了  就是jvm 对象共享  hibernate 不要求序列化，但是系统扩展缓存数据，比如ehcache Memcached 中的对象需要序列化
 *    
 *序列化保存的是对象的状态，静态变量属于类的状态，因此 序列化并不保存静态变量。
 *
 *当某些变量不想被序列化，同是又不适合使用static关键字声明，那么此时就需要用transient关键字来声明该变量。
 * transient关键字 修饰 在反序列化视图重构对象的时候，作用与static变量一样;
 * 对于某些类型的属性，其状态是瞬时的，这样的属性是无法保存其状态的。
 * 例如一个线程属性或需要访问IO、本地资源、网络资源等的属性，对于这些字段，我们必须用transient关键字标明，否则编译器将报措。
 * 
 * 当一个父类实现序列化，子类自动实现序列化，不需要显式实现Serializable接口。
一个子类实现了 Serializable 接口，它的父类都没有实现 Serializable 接口，要想将父类对象也序列化，就需要让父类也实现Serializable 接口。
 */
public class Person implements Serializable 
{
	/*
	 * 序列化 ID 在 Eclipse 下提供了两种生成策略
	 * 一个是固定的 1L
	 * 一个是随机生成一个不重复的 long 类型数据（实际上是使用 JDK 工具，根据类名、接口名、成员方法及属性等来生成）
	 * 输出对象和读入对象使用的是同一个Person类。
	 * 如果是通过网络传输的话，如果Person类的serialVersionUID不一致，那么反序列化就不能正常进行。
	 * 例如在客户端A中Person类的serialVersionUID=1L，而在客户端B中Person类的serialVersionUID=2L 那么就不能重构这个Person对象
	 */
	private static final long serialVersionUID = -21827063526963775L;
	String name;
	int age;
	
	public Person(String name,int age){
		this.name = name;
		this.age = age;
	}	
	public String toString(){
		return "name:"+name+"\tage:"+age;
	}
	
	public static void main23( String[] args )
    {
		 File file = new File("file"+File.separator+"out.txt");
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				ObjectOutputStream oos = null;
				try {
					oos = new ObjectOutputStream(fos);
					Person person = new Person("tom", 22);
					System.out.println(person);
					oos.writeObject(person);			//写入对象
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						oos.close();
					} catch (IOException e) {
						System.out.println("oos关闭失败："+e.getMessage());
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("找不到文件："+e.getMessage());
			} finally{
				try {
					fos.close();
				} catch (IOException e) {
					System.out.println("fos关闭失败："+e.getMessage());
				}
			}
									
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				ObjectInputStream ois = null;
				try {
					ois = new ObjectInputStream(fis);
					try {
						Person person = (Person)ois.readObject();	//读出对象
						System.out.println(person);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} 
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						ois.close();
					} catch (IOException e) {
						System.out.println("ois关闭失败："+e.getMessage());
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("找不到文件："+e.getMessage());
			} finally{
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("fis关闭失败："+e.getMessage());
				}
			}
    }
	
	public static void main(String[] args) {
		final char value[];
		value="zhangy".toCharArray();
		System.out.println(value.hashCode());
		System.out.println(value.length);
	}
}
