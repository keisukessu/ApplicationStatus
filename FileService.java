import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CSVファイルによるデータの永続化を担当するクラス。
 * 企業データの読み込み（load）と保存（save）を提供する。
 * ファイル形式: 「企業名,ステータス」の1行1企業のCSV。
 */
public class FileService {

    /** データを保存するCSVファイルのファイル名 */
    private static final String FILE_NAME = "companies.csv";

    /**
     * CSVファイルから企業データを読み込む。
     * ファイルが存在しない場合は空のリストを返す。
     * 各行を「,」で分割し、企業名とステータスを解析してCompanyオブジェクトを生成する。
     * @return 読み込んだ企業リスト
     */
    public static List<Company> load() {
        List<Company> companies = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return companies;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                // CSVの各行が「企業名,ステータス」の2要素であることを確認
                if (parts.length == 2) {
                    String name = parts[0];
                    Status status = Status.valueOf(parts[1]);
                    companies.add(new Company(name, status));
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading file.");
        }

        return companies;
    }

    /**
     * 企業データをCSVファイルに保存する。
     * 既存のファイル内容は上書きされる。
     * 各企業を「企業名,ステータス」の形式で1行ずつ書き出す。
     * @param companies 保存する企業リスト
     */
    public static void save(List<Company> companies) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Company c : companies) {
                pw.println(c.getName() + "," + c.getStatus());
            }

        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }
}
