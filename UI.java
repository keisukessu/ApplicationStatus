import java.util.List;
import java.util.Scanner;

/**
 * ユーザーインターフェースを担当するクラス。
 * コンソール上でメニューを表示し、ユーザーの入力に応じて各機能を呼び出す。
 */
public class UI {
    /** ビジネスロジックを処理するサービス */
    private Service service = new Service();
    /** ユーザー入力を読み取るスキャナー */
    private Scanner scanner = new Scanner(System.in);

    /**
     * アプリケーションのメインループを開始する。
     * メニューを表示し、ユーザーの選択に応じて処理を振り分ける。
     * 「0: 終了」が選択されるまでループを続ける。
     */
    public void Start() {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // nextInt()後の改行文字を消費する

            switch (choice) {
                case 1 -> addCompany();
                case 2 -> showCompanies();
                case 3 -> updateStatus();
                case 4 -> showOfferRate();
                case 0 -> exit();
                default -> System.out.println("無効な入力です");
            }
        }
    }

    /**
     * メインメニューを表示する。
     * 各機能に対応する番号と説明を出力する。
     */
    private void showMenu() {
        System.out.println("\n1: 企業追加");
        System.out.println("2: 一覧表示");
        System.out.println("3: ステータス変更");
        System.out.println("4: 内定率表示");
        System.out.println("0: 終了");
    }

    /**
     * 新しい企業を追加する。
     * ユーザーに企業名の入力を求め、Serviceに追加を委譲する。
     */
    private void addCompany() {
        System.out.print("企業名: ");
        String name = scanner.nextLine();
        service.addCompany(name);

    }

    /**
     * 登録されている全企業の一覧を表示する。
     * 「インデックス: 企業名 - ステータス」の形式で出力する。
     */
    private void showCompanies() {
        List<Company> companies = service.getCompanies();

        for (int i = 0; i < companies.size(); i++) {
            Company c = companies.get(i);
            System.out.println(i + ": " + c.getName() + " - " + c.getStatus());
        }
    }

    /**
     * 企業の応募ステータスを更新する。
     * まず企業一覧を表示し、更新対象の番号と新しいステータスをユーザーに選択させる。
     */
    private void updateStatus() {
        showCompanies();

        System.out.print("番号を選択: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // nextInt()後の改行文字を消費する

        System.out.println("1: ENTRY");
        System.out.println("2: INTERVIEW");
        System.out.println("3: OFFER");
        System.out.println("4: REJECTED");

        int statusChoice = scanner.nextInt();
        scanner.nextLine(); // nextInt()後の改行文字を消費する

        // ユーザーの選択番号をStatusに変換する
        Status status = switch (statusChoice) {
            case 1 -> Status.ENTRY;
            case 2 -> Status.INTERVIEW;
            case 3 -> Status.OFFER;
            case 4 -> Status.REJECTED;
            default -> Status.ENTRY; // 無効な入力の場合はENTRYをデフォルトにする
        };

        service.updateStatus(index, status);
    }

    /**
     * 内定率を計算して表示する。
     * 全企業のうち、内定（OFFER）を獲得した割合をパーセンテージで出力する。
     */
    private void showOfferRate() {
        double rate = service.calculateOfferRate();
        System.out.println("内定率: " + rate + "%");
    }

    /**
     * アプリケーションを終了する。
     * 終了メッセージを表示してプログラムを停止する。
     */
    private void exit() {
        System.out.println("終了します");
        System.exit(0);
    }

}
