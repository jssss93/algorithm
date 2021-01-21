package algorithm.cjs_20210120;

import java.util.ArrayList;
import java.util.List;

public class callByReference {
	public static void main(String args[]){
		int a=0;
		Integer b = new Integer(0);
		
		String str ="0";
		
		List list = new ArrayList<>();
		list.add("0");
		String[] arr = new String[2];
		arr[0] ="0";
		
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(str);
		System.out.println(list);
		System.out.println(arr[0]);
		
		System.out.println(System.identityHashCode(a));
		System.out.println(System.identityHashCode(b));
		System.out.println(System.identityHashCode(str));
		System.out.println(System.identityHashCode(list));
		System.out.println(System.identityHashCode(arr));
		
		callByReference sol = new callByReference();
		sol.test(a,b, str, list,arr);
		
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(str);
		System.out.println(list);
		System.out.println(arr[0]);
		System.out.println(System.identityHashCode(a));
		System.out.println(System.identityHashCode(b));
		System.out.println(System.identityHashCode(str));
		System.out.println(System.identityHashCode(list));
		System.out.println(System.identityHashCode(arr));
	}
	
	public void test(int a,Integer b,String str,List list,String[] arr){
		a=a+1;
		b=b+1;
		str=str+"1";
		list.add("1");
		arr[0]="1";
		System.out.println();
		System.out.println(System.identityHashCode(a));
		System.out.println(System.identityHashCode(b));
		System.out.println(System.identityHashCode(str));
		System.out.println(System.identityHashCode(list));
		System.out.println(System.identityHashCode(arr));
		System.out.println();
	}
}
