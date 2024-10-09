import java.util.Random;
import java.util.Scanner;

class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;

    public Player(String name) {
        this.name = name;
        this.maxHealth = 100; // Starting health
        this.health = maxHealth;
        this.attackPower = 20; // Base attack power
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void attack(Player opponent) {
        Random rand = new Random();
        int damage = rand.nextInt(attackPower); // Randomize attack power
        opponent.takeDamage(damage);
        System.out.println(name + " attacks " + opponent.getName() + " for " + damage + " damage!");
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0; // Prevent negative health
        System.out.println(name + " has " + health + " health remaining.");
    }

    public void heal() {
        Random rand = new Random();
        int healAmount = rand.nextInt(15) + 5; // Heal between 5 to 20 health
        health += healAmount;
        if (health > maxHealth) health = maxHealth; // Ensure health does not exceed max
        System.out.println(name + " heals for " + healAmount + " health! Current health: " + health);
    }

    public boolean isAlive() {
        return health > 0;
    }
}

public class YoHoHoGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter name for Player 1: ");
        Player player1 = new Player(scanner.nextLine());
        
        System.out.print("Enter name for Player 2: ");
        Player player2 = new Player(scanner.nextLine());

        while (player1.isAlive() && player2.isAlive()) {
            playerTurn(scanner, player1, player2);
            if (!player2.isAlive()) {
                System.out.println(player2.getName() + " has been defeated! " + player1.getName() + " wins!");
                break;
            }

            playerTurn(scanner, player2, player1);
            if (!player1.isAlive()) {
                System.out.println(player1.getName() + " has been defeated! " + player2.getName() + " wins!");
                break;
            }
        }
        scanner.close();
    }

    private static void playerTurn(Scanner scanner, Player currentPlayer, Player opponent) {
        System.out.println("\n" + currentPlayer.getName() + "'s turn!");
        System.out.println("1. Attack\n2. Heal");
        System.out.print("Choose your action: ");
        
        int choice = scanner.nextInt();

        if (choice == 1) {
            currentPlayer.attack(opponent);
        } else if (choice == 2) {
            currentPlayer.heal();
        } else {
            System.out.println("Invalid choice! You lose your turn.");
        }
        
        // Display status after turn
        System.out.println(opponent.getName() + "'s Status: " + opponent.getHealth() + " Health");
        System.out.println(currentPlayer.getName() + "'s Status: " + currentPlayer.getHealth() + " Health");
    }
}
