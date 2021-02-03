package algorithm.cjs_20210128;

public class rankSearch {
	public static void main(String args[]){
	
		String[] info = {
				"java backend junior pizza 150",
				"python frontend senior chicken 210",
				"python frontend senior chicken 150",
				"cpp backend senior pizza 260",
				"java backend junior chicken 80",
				"python backend senior chicken 50"
		};
		String[] query = {
				"java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200",
				"cpp and - and senior and pizza 250",
				"- and backend and senior and - 150",
				"- and - and - and chicken 100",
				"- and - and - and - 150"
		};
		rankSearch sol = new rankSearch();
		sol.solution(info, query);
	}
	public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for(int i=0;i<query.length;i++){
        	
        	answer[i]=0;
        	String divScore = query[i].split(" and ")[3].split(" ")[1];
        	for(int j=0;j<info.length;j++){
        		String infoScore = info[j].split(" ")[4];
        		if(Integer.parseInt(query[i].split(" and ")[3].split(" ")[1])>Integer.parseInt(info[j].split(" ")[4])){
        			continue;
        		}
        		
        		
        		if(!query[i].split(" and ")[1].equals("-")){//프론트
	        		if(!query[i].split(" and ")[1].equals(info[j].split(" ")[1])){
	        			continue;
	        		}
        		}
        		if(!query[i].split(" and ")[2].equals("-")){//주니어
	        		if(!query[i].split(" and ")[2].equals(info[j].split(" ")[2])){
	        			continue;
	        		}
        		}
        		if(!query[i].split(" and ")[3].split(" ")[0].equals("-")){//피자
	        		if(!query[i].split(" and ")[3].split(" ")[0].equals(info[j].split(" ")[3])){
	        			continue;
	        		}
        		}
        		if(!query[i].split(" and ")[0].equals("-")){//자바
	        		if(!query[i].split(" and")[0].equals(info[j].split(" ")[0])){
	        			continue;
	        		}
        		}
        		
        		answer[i]++;
        	}
        	
        	
//        	System.out.println("answer["+i+"] :: "+answer[i]);
        }
        
        return answer;
    }
	
	
}
