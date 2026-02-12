import java.util.List;
import java.util.Scanner;

public class UI {
    private Service service = new Service();
    private Scanner scanner = new Scanner(System.in);

    public void Start() {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

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

    private void showMenu() {
        System.out.println("\n1: 企業追加");
        System.out.println("2: 一覧表示");
        System.out.println("3: ステータス変更");
        System.out.println("4: 内定率表示");
        System.out.println("0: 終了");
    }

    private void addCompany() {
        System.out.print("企業名: ");
        String name = scanner.nextLine();
        service.addCompany(name);

    }

    private void showCompanies() {
        List<Company> companies = service.getCompanies();

        for (int i = 0; i < companies.size(); i++) {
            Company c = companies.get(i);
            System.out.println(i + ": " + c.getName() + " - " + c.getStatus());
        }
    }

    private void updateStatus() {
        showCompanies();

        System.out.print("番号を選択: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        System.out.println("1: ENTRY");
        System.out.println("2: INTERVIEW");
        System.out.println("3: OFFER");
        System.out.println("4: REJECTED");

        int statusChoice = scanner.nextInt();
        scanner.nextLine();

        Status status = switch (statusChoice) {
            case 1 -> Status.ENTRY;
            case 2 -> Status.INTERVIEW;
            case 3 -> Status.OFFER;
            case 4 -> Status.REJECTED;
            default -> Status.ENTRY;
        };

        service.updateStatus(index, status);
    }

    private void showOfferRate() {
        double rate = service.calculateOfferRate();
        System.out.println("内定率: " + rate + "%");
    }

    private void exit() {
        System.out.println("終了します");
        System.exit(0);
    }

}
