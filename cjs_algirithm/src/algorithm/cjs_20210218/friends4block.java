package algorithm.cjs_20210218;

import java.util.ArrayList;
import java.util.List;

//입력 형식
//입력으로 판의 높이 m, 폭 n과 판의 배치 정보 board가 들어온다.
//2 ≦ n, m ≦ 30
//board는 길이 n인 문자열 m개의 배열로 주어진다. 블록을 나타내는 문자는 대문자 A에서 Z가 사용된다.
//출력 형식
//입력으로 주어진 판 정보를 가지고 몇 개의 블록이 지워질지 출력하라.
//
//입출력 예제
//m	n	board	answer
//4	5	[CCBDE, AAADE, AAABF, CCBBF]	14
//6	6	[TTTANT, RRFACC, RRRFCC, TRRRAA, TTMMMF, TMMTTJ]	15
//예제에 대한 설명
//입출력 예제 1의 경우, 첫 번째에는 A 블록 6개가 지워지고, 두 번째에는 B 블록 4개와 C 블록 4개가 지워져, 모두 14개의 블록이 지워진다.
//입출력 예제 2는 본문 설명에 있는 그림을 옮긴 것이다. 11개와 4개의 블록이 차례로 지워지며, 모두 15개의 블록이 지워진다.
public class friends4block {
	public static void main(String args[]){
		friends4block sol = new friends4block();
		int m=6;
		int n=6;
		String[] board = {
							"HGNHU",
							"CRSHV",
							"UKHVL",
							"MJHQB",
							"GSHOT",
							"MQMJJ",
							"AGJKK",
							"QULKK"
				
				
//							"CCBDE",
//							"AAADE",
//							"AAABD",
//							"CCBBD"
				
//							"TTTANT", 
//							"RRFACC", 
//							"RRRFCC", 
//							"TRRRAA",
//							"TTMMMF",
//							"TMMTTJ"
//							"AAC",
//							"DEF",
//							"GGG"
							
						};
		System.out.println(sol.solution(m, n, board));
	}
	
	public int solution(int m, int n, String[] board) {
		Character[][] newBoard = new Character[board.length][board[0].length()];
		
		int answer = 0;
		
		//1. newBoard 생성
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[i].length();j++){
				newBoard[i][j] = board[i].charAt(j);
				System.out.print(newBoard[i][j]);
			}
			System.out.println();
		}
		List removeList = new ArrayList();
		removeList.add("init");
		
		//2. 실제 처리 proc
		while(removeList.size()>0){
			removeList = new ArrayList();
	//		List list =new ArrayList<>();
			
			//3. 4개 세트확인 ,removeList에 확인값  추가
			for(int i=0;i<board.length-1;i++){
				for(int j=0;j<board[0].length()-1;j++){
	//				System.out.print(newBoard[i][j]);
	//				System.out.print(newBoard[i][j+1]);
	//				System.out.print(newBoard[i+1][j]);
	//				System.out.print(newBoard[i+1][j+1]);
	//				System.out.println();
					if(newBoard[i][j]!='0'){
						if(newBoard[i][j]==newBoard[i][j+1] &&
								newBoard[i][j+1]==newBoard[i+1][j]&&
										newBoard[i+1][j]==newBoard[i+1][j+1]&&
											newBoard[i][j]==newBoard[i+1][j+1]){
							if(!removeList.contains(i+","+j)){
								removeList.add(i+","+j);
								answer++;
							}
							if(!removeList.contains(i+","+(j+1))){
								removeList.add(i+","+(j+1));
								
								answer++;
							}
							if(!removeList.contains((i+1)+","+j)){
								removeList.add((i+1)+","+j);
								
								answer++;
							}
							if(!removeList.contains((i+1)+","+(j+1))){
								removeList.add((i+1)+","+(j+1));
								
								answer++;
							}
	//						list.add(removeList);
						}
						
							
					}
				}
					
			}
			//4. removeList 에따른 newBoard 배열 수정
	//		System.out.println("answer=>"+answer);
	//		System.out.println();
	//		System.out.println("removeㄱㄱㄱ");
//			System.out.println(removeList);
			for(int a=removeList.size()-1;a>=0;a--){
				String target[] = (removeList.get(a)+"").split(",");
				int i = Integer.parseInt(target[0]);
				int j = Integer.parseInt(target[1]);
//				System.out.print(i+","+j+"==>");
				if(i>0){
					
//					for(int k=i-1;k>=0;k--){
//						if(k==0){
//							newBoard[i][j] = '0';
//						}
//						if(newBoard[k][j]!='0'){
//							newBoard[i][j] = newBoard[k][j];
//							newBoard[k][j] = '0';
//						}
//					}
//					System.out.println(newBoard[i][j]);
					newBoard[i][j]='0';
//					newBoard[i][j] = newBoard[i-1][j];
//					newBoard[i-1][j] = '0';
				}else{
//					System.out.println("0");
					newBoard[i][j] = '0';
				}
			}
			
			
			for(int i=1;i<board.length;i++){
				for(int j=0;j<board[0].length();j++){
					
					if(newBoard[i][j]=='0'){
						moveZero(i,j,newBoard);
//						newBoard[i][j] = newBoard[i-1][j];
//						newBoard[i-1][j]='0';
					}
				}
			}
			
//			System.out.println();
//			System.out.println("제거후==>");
			for(int i=0;i<board.length;i++){
				for(int j=0;j<board[0].length();j++){
//					System.out.print(newBoard[i][j]);
				}
//				System.out.println();
			}
		}
		
        
        return answer;
    }
	public void moveZero(int i,int j,Character[][] newBoard){
		for(int k=i;k>0;k--){
			Character temp = newBoard[k][j];
			newBoard[k][j] = newBoard[k-1][j];
			newBoard[k-1][j]=temp;
		}
	}
}
