import java.util.ArrayList;
import java.util.List;

/**
 * 企業データのビジネスロジックを担当するサービスクラス。
 * 企業の追加・一覧取得・ステータス更新・内定率計算などの操作を提供する。
 */
public class Service {
    /** 応募先企業のリスト */
    private List<Company> companies = new ArrayList<>();

    /**
     * コンストラクタ。CSVファイルから既存の企業データを読み込んで初期化する。
     */
    public Service() {
        companies = FileService.load();
    }

    /**
     * 新しい企業を追加する。初期ステータスはENTRY（エントリー済み）。
     * 追加後、自動的にCSVファイルへ保存する。
     * @param name 追加する企業名
     */
    public void addCompany(String name) {
        companies.add(new Company(name, Status.ENTRY));
        save();
    }

    /**
     * 登録されている全企業のリストを取得する。
     * @return 企業リスト
     */
    public List<Company> getCompanies() {
        return companies;
    }

    /**
     * 指定したインデックスの企業のステータスを更新する。
     * インデックスが範囲外の場合は何もしない。
     * 更新後、自動的にCSVファイルへ保存する。
     * @param index  更新対象の企業インデックス（0始まり）
     * @param status 新しいステータス
     */
    public void updateStatus(int index, Status status) {
        if (0 <= index && index < getCompanies().size()) {
            companies.get(index).setStatus(status);
            save();
        }
    }

    /**
     * 内定率を計算する。
     * 内定（OFFER）の企業数 ÷ 全企業数 × 100 をパーセンテージで返す。
     * 企業が0件の場合は0.0を返す。
     * @return 内定率（パーセント）
     */
    public double calculateOfferRate() {
        if (companies.isEmpty()) {
            return 0;
        } else {
            int offerCount = 0;
            for (Company c : companies) {
                if (c.getStatus().equals(Status.OFFER)) {
                    offerCount++;
                }
            }
            return (double) offerCount / companies.size() * 100;
        }
    }

    /**
     * 現在の企業リストをCSVファイルに保存する。
     */
    public void save() {
        FileService.save(companies);
    }

}
