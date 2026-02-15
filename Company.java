/**
 * 応募先の企業を表すモデルクラス。
 * 企業名と応募ステータスを保持する。
 */
public class Company {
    /** 企業名 */
    private String name;
    /** 現在の応募ステータス */
    private Status status;

    /**
     * コンストラクタ。企業名とステータスを指定して企業オブジェクトを生成する。
     * @param name   企業名
     * @param status 応募ステータス
     */
    public Company(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    /**
     * 企業名を取得する。
     * @return 企業名
     */
    public String getName() {
        return name;
    }

    /**
     * 応募ステータスを取得する。
     * @return 現在のステータス
     */
    public Status getStatus() {
        return status;
    }

    /**
     * 応募ステータスを更新する。
     * @param status 新しいステータス
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
