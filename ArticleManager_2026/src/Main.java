import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("프로그램 시작");
    Scanner sc = new Scanner(System.in);
    int article_count = 0;

    while (true) {
      System.out.println("명령어 ) ");
      String cmd = sc.nextLine().trim();

      if (cmd.equals("article write")) {
        article_count = article_count + 1;
        System.out.println("제목을 입력하세요");
        String article_header = sc.nextLine();
        System.out.println("내용을 입력하세요");
        String article_detail = sc.nextLine();

        System.out.println(article_count + "번 글이 생성되었습니다");
      }

      if (cmd.equals("exit")) {
        break;
      } else if (cmd.length() == 0) {
        System.out.println("명령어를 입력해주세요");
        continue;
      }
    }
    System.out.println("프로그램 종료");
    sc.close();
  }
}

class Article {
  private int id;
  private String title;
  private String body;

  public Article(int id, String title, String body) {

  }
}
