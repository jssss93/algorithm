package algorithm2.cjs_20211124;

import java.util.HashSet;

//n���� ����ž�� ������ ���� �ϳ��� Ʈ�� ���·� ����Ǿ� �ֽ��ϴ�. ����� �� ������ �� �ϳ��� ��� ������ ���¸� ��Ʈ��ũ�� 2���� �����Ϸ��� �մϴ�. 
//�̶�, �� ���¸��� ���� �Ǵ� ����ž�� ������ �ִ��� ����ϰ� ���߰��� �մϴ�.
//
//����ž�� ���� n, �׸��� ���� ���� wires�� �Ű������� �־����ϴ�. 
//������ �� �ϳ��� ��� ����ž ������ ������ ����ϵ��� �� ���¸����� �������� ��, 
//�� ���¸��� ������ �ִ� ����ž ������ ����(���밪)�� return �ϵ��� solution �Լ��� �ϼ����ּ���.
//
//���ѻ���
//n�� 2 �̻� 100 ������ �ڿ����Դϴ�.
//wires�� ���̰� n-1�� ������ 2���� �迭�Դϴ�.
//wires�� �� ���Ҵ� [v1, v2] 2���� �ڿ����� �̷���� ������, �̴� ���¸��� v1�� ����ž�� v2�� ����ž�� �������� ����Ǿ� �ִٴ� ���� �ǹ��մϴ�.
//1 �� v1 < v2 �� n �Դϴ�.
//���¸� ��Ʈ��ũ�� �ϳ��� Ʈ�� ���°� �ƴ� ���� �Է����� �־����� �ʽ��ϴ�.
//����� ��
//n	wires	result
//9	[[1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9]]	3
//4	[[1,2},{2,3},{3,4]]	0
//7	[[1,2},{2,7},{3,7},{3,4},{4,5},{6,7]]	1
public class ���¸������� {
	public static void main(String args[]) {
//		int n = 10;
//		int[][] wires = {
//				{1,3},
//				{2,3},
//				{3,4},
//				{4,5},
//				{4,6},
//				{4,7},
//				{7,8},
//				{7,9},
//				{7,10}
////				{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}
//		};
		
//		3 / [[1,2},{2,3]] 
		int n = 8;
		int[][] wires = {
				{1,2},{1,3},{1,4},{4,5},{5,6},{6,7},{6,8}
//				{1,2},
//				{2,3},
//				{3,4},
//				{4,5}
		};
		solution(n, wires);
	}
    public static int solution(int n, int[][] wires) {
        int answer = n;
        double min_all = n;
        for(int i=0;i<wires.length;i++) {
        	HashSet<Integer> list = new HashSet<>();
        	for(int j=0;j<wires.length;j++) {
        		
        		if(i!=j) {
        			if(list.size()==0) {
	                	list.add(wires[j][0]);
	                	list.add(wires[j][1]);
        			}else {
        				if(list.contains(wires[j][0]) || list.contains(wires[j][1])) {
        					list.add(wires[j][0]);
    	                	list.add(wires[j][1]);
        				}
        			}
        		}
        		
        	}
        	System.out.println(list);
//        	System.out.println("list.size : "+(list.size())+",,  ��ü/2 : "+(double)n/2+",, ����::"+((double)list.size()-(double)n/2));
        	
//        	
        		//size - �߰��� �� �ּ��ΰ� ã���� �ȴ�.
//        	System.out.println(min_all+",,"+Math.abs((double)list.size()-(double)n/2));
        		if(min_all>Math.abs((double)list.size()-(double)n/2)) {
        			min_all = Math.abs((double)list.size()-(double)n/2);
    				answer = (int) (n-list.size()-list.size());
    				
        			System.out.println("�ּҳ�?");
        			
        			System.out.println("���� : " + Math.abs(n-list.size()-list.size()));
//        			System.out.println(answer);
        		}
//        	}else {
//        		
//        	}
        		answer = Math.abs(answer);
        	System.out.println("\n");
        }
//        answer = 
        System.out.println("answer = >" + answer);
        return answer;
    }
}
