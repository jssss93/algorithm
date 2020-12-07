package fileSearch;

import java.io.File;
import java.io.IOException;

public class search {
	public static void main(String args[]) {

		subDirList("C:/bracketCheck");
	}

	public static void subDirList(String source) {

		File dir = new File(source);

		File[] fileList = dir.listFiles();

		try {

			for (int i = 0; i < fileList.length; i++) {

				File file = fileList[i];

				if (file.isFile()) {

					// 파일이 있다면 파일 이름 출력

//					System.out.println("\t 파일 이름 = " + file.getName());
					String fileName = file.getName();
					int pos = fileName .lastIndexOf(".");
					String ext = fileName.substring(pos+1, fileName.length());
					if(ext.equals("xml")){
						System.out.println(file.getCanonicalPath() );
//						System.out.println(fileName);
						System.out.println("대리님 찾는 파일 맞나요?");
					}
					
//					String ext = file.getName().split(".")[0];
//					System.out.println(ext);
				} else if (file.isDirectory()) {

//					System.out.println("디렉토리 이름 = " + file.getName());

					// 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색

					subDirList(file.getCanonicalPath().toString());

				}

			}

		} catch (IOException e) {

		}

	}

}
