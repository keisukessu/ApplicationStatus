import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<Company> companies = new ArrayList<>();

    public void addCompany(String name) {
        companies.add(new Company(name, Status.ENTRY));
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void updateStatus(int index, Status status) {
        if (0 <= index && index < getCompanies().size()) {
            companies.get(index).setStatus(status);
        }
    }

    public double culculateOfferRates() {
        if (companies.isEmpty()) {
            return 0;
        } else {
            int offerCount = 0;
            for (Company c : companies) {
                if (c.getStatus() == Status.OFFER) {
                    offerCount++;
                }
            }
            return (offerCount / companies.size()) * 100;
        }
    }
}
