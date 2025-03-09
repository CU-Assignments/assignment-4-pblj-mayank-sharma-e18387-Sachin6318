import java.util.*;

class CardCollection {
    private Map<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    public void addCard(String symbol, String card) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, new ArrayList<>());
    }

    public void displayAllCards() {
        if (cardMap.isEmpty()) {
            System.out.println("No cards in the collection.");
            return;
        }
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

public class CardCollectionSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Card Symbol (e.g., Hearts, Spades): ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter Card Name (e.g., Ace, King, 2, 10): ");
                    String card = scanner.nextLine();
                    collection.addCard(symbol, card);
                    System.out.println("Card added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Card Symbol to search: ");
                    String searchSymbol = scanner.nextLine();
                    List<String> cards = collection.getCardsBySymbol(searchSymbol);
                    if (cards.isEmpty()) {
                        System.out.println("No cards found for symbol: " + searchSymbol);
                    } else {
                        System.out.println("Cards in " + searchSymbol + ": " + cards);
                    }
                    break;
                case 3:
                    collection.displayAllCards();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
