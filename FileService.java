import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private static final String FILE_NAME = "companies.csv";

    // CSV読み込み
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

    // CSV保存
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
