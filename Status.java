/**
 * 企業への応募ステータスを表す列挙型。
 * 応募の進捗状況を4段階で管理する。
 */
public enum Status {
    /** エントリー済み（応募段階） */
    ENTRY,
    /** 面接中 */
    INTERVIEW,
    /** 内定 */
    OFFER,
    /** 不採用 */
    REJECTED
}
