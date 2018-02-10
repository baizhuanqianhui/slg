import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Test {
  public static void main(String[] args) {
	Map<String,String>  map = new LinkedHashMap<String,String>();
	map.put("test1", "a");
	map.put("test2", "b");
	map.put("test3", "c");
	map.put("test4", "d");
	map.put("test5", "e");
	Map<String,String>  map1 = new LinkedHashMap<String,String>();
	map1.put("test2", "f");
	map1.put("test3", "g");
	map1.put("test1", "h");
	map1.put("test4", "i");
	map1.put("test5", "k");
	Map<String,String>  map2 = new LinkedHashMap<String,String>();
	map2.put("test1", "l");
	map2.put("test2", "m");
	map2.put("test3", "n");
	map2.put("test4", "o");
	map2.put("test5", "p");
	List list = new ArrayList();
	list.add(map);
	list.add(map1);
	list.add(map2);
	for(int i=0;i<list.size();i++){
		Map mapList = (Map) list.get(i);
		
	   for (Iterator it =  mapList.keySet().iterator();it.hasNext();) {
	    Object key = it.next();
	    System.out.print( key+"="+ mapList.get(key)+" ");
	   }
	   System.out.println("/n");
	}
}
}
