package algorithm2.cjs_20211124;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 행렬_테두리_회전하기 {
	public static void main(String args[]) {
		int rows=6;
		int columns=6;
		int[][] queries= {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
		
		
//		int rows=3;
//		int columns=3;
//		int[][] queries= {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};
		
		
//		int rows=5;
//		int columns=5;
//		int[][] queries= {{2,2,3,3}};
		
				
				
		solution(rows, columns, queries);
	}
	
	public static int[] solution(int rows, int columns, int[][] queries) {
		
		int[] answer = new int[queries.length];
        int[][] square = new int[rows][columns];
		
        int p=1;
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				square[i][j] = p++;
			}
		}
		for(int k=0;k<queries.length;k++) {
			List ansList = new ArrayList<Integer>();
			
			if(k==queries.length-1) {
				square = change_square(queries[k], square,ansList);
			}else {
				square = change_square(queries[k], square,ansList);
			}
			
			Collections.sort(ansList);
			answer[k] = (int) ansList.get(0);
		}
		
		
		for(int a=0;a<answer.length;a++) {
			System.out.println(answer[a]);
		}
		
        
        return answer;
    }
	public static int[][] change_square(int[] quiry,int[][] square,List ansList) {
		int [][] ch_square =new int[square.length][square[0].length];
		for(int aa=0;aa<square.length;aa++) {
			for(int bb=0;bb<square[0].length;bb++) {
				ch_square[aa][bb] = square[aa][bb];
			}
		}
				 
		int x1 =quiry[0]-1;//2 
		int y1 =quiry[1]-1;//2
		int x2 =quiry[2]-1;//5
		int y2 =quiry[3]-1;//4
		
        
        
        int print_y = x2-x1;
        int print_x = y2-y1;
        int k = 1;
        int right = y1;
        int bottom = x1;
        int top = 1;
        
        for(int i=0; i<2; i++) {
            for(int j=0; j<print_x; j++) {
                right += top;
//                System.out.println(bottom+",,"+right+"=="+(bottom)+",,"+(right-top));
                ch_square[bottom][right]=square[bottom][right-top];
                ansList.add(ch_square[bottom][right]);
            }
//            System.out.println("**");
            
            for(int j=0; j<print_y; j++) {
                bottom += top;
//                System.out.println(bottom+",,"+right +"<=="+(bottom-top)+",,"+(right));
                ch_square[bottom][right]=square[bottom-top][right];
                ansList.add(ch_square[bottom][right]);
            }
            
            top = top * (-1);
//            System.out.println("=========\n");
        }
        return ch_square;
		
	}
	
//	public static int[][] getSquare(int rows,int columns){
//		
//		int[][] square = new int[rows][columns];
//		
//		for(int i=0;i<rows;i++) {
//			for(int j=0;j<columns;j++) {
//				square[i][j] = i*columns+rows;
//			}
//		}
//		
//		return square;
//	}
}
