package com.blackoon.lr.jdk.collection;

/**
 * @author zhangy
 * @E-mail:blackoon88@gmail.com
 * @qq:846579287
 * @version 创建时间：2016年4月27日 下午5:04:23 添加对象的容器，底层使用数组模拟
 *          与数组的好处是：不用考虑数组的边界问题，可以动态的扩展
 */
public class ArrayList {
	
	Object[] objects = new Object[10]; // 定义一个长度为10的数组
	int index = 0; // 数组索引指向

	/**
	 * 数组中添加元素
	 * 
	 * @param o 要添加的元素
	 */
	public void add(Object o) {
		// 当原数组数据满时再开辟两倍长度的新数组，并拷贝原数组数据至新数组中，并将原数组指向新数组
		if (index == objects.length) {
			Object[] newObjects = new Object[objects.length * 2];
			System.arraycopy(objects, 0, newObjects, 0, objects.length);
			objects = newObjects;
		}
		objects[index] = o; // 元素添加到数组
		index++; // 移动索引指向
	}
	public void remove(Object o){
		Object[] newObjects=new Object[objects.length-1];
		int i=0;
		for(Object object:objects){
			if(!o.equals(object)){
				newObjects[i]=object;
			}
			i++;
		}
		index--;
	}
	
	/**
	 * 数组中元素的个数
	 * 
	 * @return 返回数组中元素的个数
	 */
	public int size() {
		return index;
	}
}
