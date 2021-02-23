package etc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class test {
	public static void main(String args[]){
		Map codeCacheMap =new HashMap(); 
		List<Map> cateList = new ArrayList<>();
		List<Map> codeList= new ArrayList<>();
		
		for(int i=0;i<10;i++){
			Map map = new HashMap();
			map.put("cate_id", "val"+i);
			cateList.add(map);
		}
		
		for(int i=0;i<5;i++){
			Map map2 = new HashMap();
			map2.put("cate_id", "val"+i);
			codeList.add(map2);
		}
		
		
		for(int i = 0 ; i < cateList.size(); i++) {
			Map cate = cateList.get(i);
			List<Map> detailCodeList = codeList.stream()
					.filter(m -> m.get("cate_id").equals(cate.get("cate_id")))
					.collect(Collectors.toList());
			codeCacheMap.put(cate.get("cate_id"), detailCodeList);
		}
		
		
		System.out.println(codeCacheMap);
	}
	
	
}