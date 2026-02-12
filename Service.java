import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<Company> companies = new ArrayList<>();

    public Service() {
        companies = FileService.load(); // 起動時読み込み
    }

    public void addCompany(String name) {
        companies.add(new Company(name, Status.ENTRY));
        save(); // 保存してから終了
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void updateStatus(int index, Status status) {
        if (0 <= index && index < getCompanies().size()) {
            companies.get(index).setStatus(status);
            save();
        }
    }

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

    public void save() {
        FileService.save(companies); // 終了時保存
    }

}
