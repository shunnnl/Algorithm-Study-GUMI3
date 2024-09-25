import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 22859. HTML 파싱
 * 메모리 : 23432 KB
 * 시간 : 344 ms
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String html = br.readLine();

        StringBuilder sb = new StringBuilder();
        int size = html.length();
        int idx = 0;

        while (idx < size) {
            // 1. <div title="..."> 처리
            if (html.startsWith("<div title=", idx)) {
                idx += 12;  // <div title=" 이후로 인덱스를 이동
                StringBuilder title = new StringBuilder();
                while (html.charAt(idx) != '"') {
                    title.append(html.charAt(idx++));
                }
                idx++;  // "를 넘김
                sb.append("title : ").append(title.toString()).append("\n");


            }
            // 2. <p> 태그 처리
            else if (html.startsWith("<p>", idx)) {
                idx += 3;  // <p>를 넘김
                StringBuilder p = new StringBuilder();

                // <p>와 </p> 사이의 텍스트 추출
                while (!html.startsWith("</p>", idx)) {
                    if (html.charAt(idx) == '<') {
                        while (html.charAt(idx) != '>') {
                            idx++;
                        }
                        idx++;  // '>'를 넘김
                    } else {
                        p.append(html.charAt(idx++));
                    }
                }
                idx += 4;  // </p>를 넘김

                // 추출된 텍스트에서 공백 정리
                String tmp = p.toString().trim();
                sb.append(tmp.replaceAll("\\s+", " ")).append("\n");

            } else {
                idx++;
            }
        }

        System.out.println(sb.toString().trim());
    }
}