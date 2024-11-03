import java.util.Scanner;

class Artist {
    private String name;            // Private fields: abstract implementation
    private int fanBase;
    private double money;

    private static int totalArtists = 0;
    private static double totalMoneyEarned = 0;
    private static int totalFans = 0;

    // Constructor: public access
    public Artist(String name, int fanBase, double money) {
        this.name = name;
        this.fanBase = fanBase;
        this.money = money;
        totalArtists++;
        totalMoneyEarned += money;
        totalFans += fanBase;
    }

    // I am setting Public getter and setter methods to  use abstraction
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

    public void releaseSongs(Song[] songs) {
        for (Song song : songs) {
            int popularity = song.calculatePopularity();
            int fansGained = popularity * 10;
            setFanBase(getFanBase() + fansGained);
            setMoney(getMoney() + popularity * 50);
            totalMoneyEarned += popularity * 50;
            totalFans += fansGained;
            System.out.println(name + " released '" + song.getTitle() + "' and gained " + fansGained + " fans!");
        }
    }

    public void performConcert() {
        int concertFans = 50;
        setFanBase(getFanBase() + concertFans);
        setMoney(getMoney() + 1000);
        totalMoneyEarned += 1000;
        totalFans += concertFans;
        System.out.println(name + " performed at a concert and gained " + concertFans + " fans!");
    }

    public static void displayTotalStats() {
        System.out.println("\n--- Total Stats ---");
        System.out.println("Total Artists: " + totalArtists);
        System.out.println("Total Money Earned by All Artists: $" + totalMoneyEarned);
        System.out.println("Total Fans Gained by All Artists: " + totalFans);
    }
}

class Song {
    private String title;   // Private fields
    private String style;
    private int quality;

    // Constructor
    public Song(String title, String style, int quality) {
        this.title = title;
        this.style = style;
        this.quality = quality;
    }

    // Public getters and setters
    public String getTitle() {
        return title;
    }

    public String getStyle() {
        return style;
    }

    public int getQuality() {
        return quality;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int calculatePopularity() {
        return quality * 10;
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

            artists[j] = new Artist(artistName, initialFanBase, initialMoney);

            System.out.print("Enter the number of songs: ");
            int numberOfSongs = scanner.nextInt();
            scanner.nextLine();

            Song[] songs = new Song[numberOfSongs];

            for (int i = 0; i < numberOfSongs; i++) {
                System.out.println("Enter details for song " + (i + 1) + ":");

                System.out.print("Enter song title: ");
                String songTitle = scanner.nextLine();

                System.out.print("Enter song style: ");
                String songStyle = scanner.nextLine();

                System.out.print("Enter song quality (1-10): ");
                int songQuality = scanner.nextInt();
                scanner.nextLine();

                songs[i] = new Song(songTitle, songStyle, songQuality);
            }

            System.out.println("\nInitial Artist Details:");
            System.out.println("Name: " + artists[j].getName() + ", Fan Base: " + artists[j].getFanBase() + ", Money: $" + artists[j].getMoney());

            artists[j].releaseSongs(songs);

            artists[j].performConcert();

            System.out.println("\nFinal Artist Details:");
            System.out.println("Name: " + artists[j].getName() + ", Fan Base: " + artists[j].getFanBase() + ", Money: $" + artists[j].getMoney());
        }

        Artist.displayTotalStats();

        scanner.close();
    }
}
