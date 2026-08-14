import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("프로그램 시작");
    Scanner sc = new Scanner(System.in);
    int article_count = 0;

    List<Article> articles = new ArrayList<>();
    articles.add(new Article(1, "테스트 제목 1", "테스트 내용 1", "테스트 시간1"));
    articles.add(new Article(2, "테스트 제목 2", "테스트 내용 2", "테스트 시간2"));
    articles.add(new Article(3, "테스트 제목 3", "테스트 내용 3", "테스트 시간3"));

    article_count = 3;

    while (true) {
      System.out.println("명령어 ) ");
      String cmd = sc.nextLine().trim();

      /* 게시글 삭제 */
      if (cmd.startsWith("article delete ")) {
        String delete_count_id = cmd.replace("article delete ", "");
        int delete_count = Integer.parseInt(delete_count_id.trim());

        Article target_delete_article = null;
        for (Article article : articles) {
          if (article.getArticle_id() == delete_count) {
            target_delete_article = article;
            break;
          }
        }
        if (target_delete_article == null) {
          System.out.println("해당 게시글은 없습니다.");
          continue;
        }
        articles.remove(target_delete_article);
        System.out.printf("%d번 게시글이 삭제되었습니다.\n", delete_count);
      }

      /* 게시글 수정 */
      if (cmd.startsWith("article modify")) {
        String modify_count_id = cmd.replace("article modify ", "");
        int modify_id = Integer.parseInt(modify_count_id);

        Article target_modify_article = null;
        for (Article article : articles) {
          if (article.getArticle_id() == modify_id) {
            target_modify_article = article;
            break;
          }
        }
        if (target_modify_article == null) {
          System.out.println("해당 게시글은 없습니다.");
          continue;
        }
        System.out.println("기존 제목 : " + target_modify_article.getArticle_header());
        System.out.println("기존 내용 : " + target_modify_article.getArticle_detail());
        System.out.print("제목 : ");
        String modify_header = sc.nextLine().trim();
        System.out.print("내용 : ");
        String modify_detail = sc.nextLine().trim();
        target_modify_article.setArticle_header(modify_header);
        target_modify_article.setArticle_detail(modify_detail);
        System.out.printf("%d번 게시글이 수정되었습니다.\n", target_modify_article.getArticle_id());
      }


      /* 게시글 상세보기 */
      if (cmd.startsWith("article detail ")) {
        int detail_id = Integer.parseInt(cmd.split(" ")[2]);
        Article target_detail_article = null;

        for (Article article : articles) {
          if (article.getArticle_id() == detail_id) {
            target_detail_article = article;
            break;
          }
        }
        if (target_detail_article == null) {
          System.out.println("해당 게시글은 없습니다.");
          continue;
        }
        System.out.println("번호 : " + target_detail_article.getArticle_id());
        System.out.println("날짜 : " + target_detail_article.getArticle_time());
        System.out.println("제목 : " + target_detail_article.getArticle_header());
        System.out.println("내용 : " + target_detail_article.getArticle_detail());
        System.out.println();
      }


      /* 게시글 작성 */
      if (cmd.equals("article write")) {
        System.out.println("== 게시글 작성 ==");
        int article_id = article_count + 1;
        System.out.println("제목을 입력하세요");
        String article_header = sc.nextLine().trim();

        System.out.println("내용을 입력하세요");
        String article_detail = sc.nextLine().trim();

        DateTimeFormatter DateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String article_time = LocalDateTime.now().format(DateTimeFormat);

        Article article = new Article(article_id, article_header, article_detail, article_time);
        articles.add(article);

        System.out.printf("%d번 글이 작성되었습니다.\n", article_id);
        article_count++;
      } else if (cmd.equals("article list")) {
        System.out.println("==게시물 목록==");
        if (articles.size() == 0) {
          System.out.println("게시글이 존재하지 않습니다.");
        } else {
          System.out.println("  번호  /   제목   /   내용  ");
          for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.printf("  %d  /   %s   /  %s   \n", article.getArticle_id(), article.getArticle_header(), article.getArticle_detail());
          }
        }
      }
      /* 프로그램 종료 */
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
  private int article_id;
  private String article_header;
  private String article_detail;
  private String article_time;

  public int getArticle_id() {
    return article_id;
  } // getter

  public String getArticle_header() {
    return article_header;
  }

  public String getArticle_detail() {
    return article_detail;
  }

  public String getArticle_time() {
    return article_time;
  }

  public void setArticle_id(int article_id) {
    this.article_id = article_id;
  } // setter

  public void setArticle_header(String article_header) {
    this.article_header = article_header;
  }

  public void setArticle_detail(String article_detail) {
    this.article_detail = article_detail;
  }

  public void setArticle_time(String article_time) {
    this.article_time = article_time;
  }

  public Article(int article_id, String article_header, String article_detail, String article_time) {
    this.article_id = article_id;
    this.article_header = article_header;
    this.article_detail = article_detail;
    this.article_time = article_time;
  }

  public String toString() {
    return article_id + " " + article_header + " " + article_detail + " " + article_time;
  }
}
