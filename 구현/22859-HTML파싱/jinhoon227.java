package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 문제: HTML 파싱 (B22859)
 * 풀이: 정규표현식
 * 메모리: 32360kb
 * 시간: 548ms
 */
public class B22859 {

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        String s = br.readLine();

        // <div> 태그 매칭, group1 에는 title 내용, group2 에는 div 가 가진 내용이 담기는 파서 생성
        Pattern divPattern = Pattern.compile("<div title=\"(.*?)\">(.*?)</div>", Pattern.DOTALL);
        Matcher divMatcher = divPattern.matcher(s);

        // <div> 태그를 파싱한다.
        while (divMatcher.find()) {
            String title = divMatcher.group(1);
            String content = divMatcher.group(2);

            result.append("title : ").append(title).append("\n");

            // <p> 태그 안에 있는 내용 추출 파서 생성
            Pattern pPattern = Pattern.compile("<p>(.*?)</p>", Pattern.DOTALL);
            Matcher pMatcher = pPattern.matcher(content);

            // <p> 태그를 파싱한다.
            while (pMatcher.find()) {
                String paragraph = pMatcher.group(1);

                // <p> 태그안에 모든 태그 제거
                paragraph = paragraph.replaceAll("<[^>]+>", "");

                // 문장 시작과 끝에 공백이 있다면 제거
                paragraph = paragraph.trim();

                // 문장에 공백이 2개 이상 존재한다면 공백 하나로 변경
                paragraph = paragraph.replaceAll(" {2,}", " ");

                result.append(paragraph).append("\n");
            }
        }

        System.out.println(result);
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B22859().solution();
//    }
//}
