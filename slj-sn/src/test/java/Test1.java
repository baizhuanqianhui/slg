import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Test1 {
	
    public static void main(String[] args) {
    	 List<Integer> array=new ArrayList<Integer>();
    	 List<Integer> linked=new LinkedList<Integer>();
    	String a="123";
    	String b= new String("123");
    	System.out.println(a.equals(b));
    	for(int i=0;i<100000;i++){
    		array.add(i);
    		linked.add(i);
    	}
    	/*System.out.println("array time:"+getTime(array));
        System.out.println("linked time:"+getTime(linked));*/
        System.out.println("array insert time:"+insertTime(array));
      //  array.clear();
        System.out.println("linked insert time:"+insertTime(linked));
		
    }
     public static  long getTime(List list){
    	long time=System.currentTimeMillis();
    	for(int i=0;i<100000;i++){
    	 int index=Collections.binarySearch(list,list.get(i));
    	  if(index!=i){
    	   System.out.println("ERROR!");
    	  }
    	}
    	return System.currentTimeMillis()-time;
    }
   public static  long insertTime(List list){
	   long time=System.currentTimeMillis();
       for(int i=0;i<100000;i++){
         list.add(15000,i);
    	  // list.remove(i);
       }
    return System.currentTimeMillis()-time;
   }
 
}
