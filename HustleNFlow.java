import java.util.Scanner;

// Interface for different types of concerts
interface ConcertType {
    int getFanGain();
    double getMoneyEarned();
}

class StandardConcert implements ConcertType {
    private int fanGain;
    private double moneyEarned;

    public StandardConcert(int fanGain, double moneyEarned) {
        this.fanGain = fanGain;
        this.moneyEarned = moneyEarned;
    }

    @Override
    public int getFanGain() {
        return fanGain;
    }

    @Override
    public double getMoneyEarned() {
        return moneyEarned;
    }
}

class CharityConcert implements ConcertType {
    private int fanGain;
    private double moneyEarned;

    public CharityConcert(int fanGain, double moneyEarned) {
        this.fanGain = fanGain;
        this.moneyEarned = moneyEarned * 0.5; // Adjusted earnings for charity
    }

    @Override
    public int getFanGain() {
        return fanGain;
    }

    @Override
    public double getMoneyEarned() {
        return moneyEarned;
    }
}

abstract class Song {
    private String title;
    private String style;

    public Song(String title, String style) {
        this.title = title;
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public abstract int calculatePopularity();
}

class StandardSong extends Song {
    private int quality;

    public StandardSong(String title, String style, int quality) {
        super(title, style);
        this.quality = quality;
    }

    @Override
    public int calculatePopularity() {
        return quality * 10;
    }
}

class ViralSong extends Song {
    private int quality;

    public ViralSong(String title, String style, int quality) {
        super(title, style);
        this.quality = quality;
    }

    @Override
    public int calculatePopularity() {
        return quality * 20; // Higher popularity multiplier
    }
}

abstract class Artist {
    private String name;
    private int fanBase;
    private double money;

    public Artist(String name, int fanBase, double money) {
        this.name = name;
        this.fanBase = fanBase;
        this.money = money;
        Statistics.incrementArtistCount();
    }

    public abstract void performConcert(ConcertType concert);

    public void releaseSong(Song song) {
        int popularity = song.calculatePopularity();
        int fansGained = popularity * 10;
        double moneyEarned = popularity * 50;

        fanBase += fansGained;
        money += moneyEarned;

        Statistics.updateArtistStats(fansGained, moneyEarned);
        System.out.println(name + " released '" + song.getTitle() + "' and gained " + fansGained + " fans!");
    }

    public String getName() {
        return name;
    }

    public int getFanBase() {
        return fanBase;
    }

    public double getMoney() {
        return money;
    }

    public void setFanBase(int fanBase) {
        this.fanBase = fanBase;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}

class Singer extends Artist {
    private String genre;

    public Singer(String name, int fanBase, double money, String genre) {
        super(name, fanBase, money);
        this.genre = genre;
    }

    @Override
    public void performConcert(ConcertType concert) {
        setFanBase(getFanBase() + concert.getFanGain());
        setMoney(getMoney() + concert.getMoneyEarned());
        Statistics.updateArtistStats(concert.getFanGain(), concert.getMoneyEarned());
        System.out.println(getName() + " performed a concert and gained " + concert.getFanGain() + " fans!");
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

class Statistics {
    private static int totalArtists = 0;
    private static double totalMoneyEarned = 0;
    private static int totalFans = 0;

    public static void updateArtistStats(int fans, double money) {
        totalFans += fans;
        totalMoneyEarned += money;
    }

    public static void incrementArtistCount() {
        totalArtists++;
    }

    public static void displayTotalStats() {
        System.out.println("\n--- Total Stats ---");
        System.out.println("Total Artists: " + totalArtists);
        System.out.println("Total Money Earned by All Artists: $" + totalMoneyEarned);
        System.out.println("Total Fans Gained by All Artists: " + totalFans);
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of artists: ");
        int numberOfArtists = scanner.nextInt();
        scanner.nextLine();

        Artist[] artists = new Artist[numberOfArtists];

        for (int j = 0; j < numberOfArtists; j++) {
            System.out.println("\nEnter details for artist " + (j + 1) + ":");

            System.out.print("Enter artist name: ");
            String artistName = scanner.nextLine();

            System.out.print("Enter initial fan base: ");
            int initialFanBase = scanner.nextInt();

            System.out.print("Enter initial money: ");
            double initialMoney = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter genre: ");
            String genre = scanner.nextLine();

            artists[j] = new Singer(artistName, initialFanBase, initialMoney, genre);

            System.out.print("Enter the number of songs: ");
            int numberOfSongs = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < numberOfSongs; i++) {
                System.out.println("Enter details for song " + (i + 1) + ":");

                System.out.print("Enter song title: ");
                String songTitle = scanner.nextLine();

                System.out.print("Enter song style: ");
                String songStyle = scanner.nextLine();

                System.out.print("Enter song quality (1-10): ");
                int songQuality = scanner.nextInt();
                scanner.nextLine();

                Song song = i % 2 == 0
                        ? new StandardSong(songTitle, songStyle, songQuality)
                        : new ViralSong(songTitle, songStyle, songQuality);

                artists[j].releaseSong(song);
            }

            ConcertType concert = j % 2 == 0
                    ? new StandardConcert(100, 2000)
                    : new CharityConcert(150, 1500);

            artists[j].performConcert(concert);

            System.out.println("\nFinal Artist Details:");
            System.out.println("Name: " + artists[j].getName() + ", Fan Base: " + artists[j].getFanBase() + ", Money: $" + artists[j].getMoney());
        }

        Statistics.displayTotalStats();
        scanner.close();
    }
}
