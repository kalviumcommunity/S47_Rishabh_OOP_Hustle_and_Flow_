import java.util.Scanner;


abstract class Artist {
    private String name;
    private int fanBase;
    private double money;

    private static int totalArtists = 0;
    private static double totalMoneyEarned = 0;
    private static int totalFans = 0;

    
    public Artist(String name, int fanBase, double money) {
        this.name = name;
        this.fanBase = fanBase;
        this.money = money;
        totalArtists++;
        totalMoneyEarned += money;
        totalFans += fanBase;
    }

  
    public abstract void performConcert();


    public void releaseSongs(Song song) {
        int popularity = song.calculatePopularity();
        int fansGained = popularity * 10;
        fanBase += fansGained;
        money += popularity * 50;
        totalMoneyEarned += popularity * 50;
        totalFans += fansGained;
        System.out.println(name + " released '" + song.getTitle() + "' and gained " + fansGained + " fans!");
    }

    
    public static void displayTotalStats() {
        System.out.println("\n--- Total Stats ---");
        System.out.println("Total Artists: " + totalArtists);
        System.out.println("Total Money Earned by All Artists: $" + totalMoneyEarned);
        System.out.println("Total Fans Gained by All Artists: " + totalFans);
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
    public void performConcert() {
        int concertFans = 100;
        setFanBase(getFanBase() + concertFans);
        setMoney(getMoney() + 2000);
        System.out.println(getName() + " performed at a concert and gained " + concertFans + " fans!");
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}


class Song {
    private String title;
    private String style;
    private int quality;

    public Song(String title, String style, int quality) {
        this.title = title;
        this.style = style;
        this.quality = quality;
    }

    public String getTitle() {
        return title;
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

            System.out.print("Enter genre: ");
            String genre = scanner.nextLine();

            
            artists[j] = new Singer(artistName, initialFanBase, initialMoney, genre);

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

           
            for (Song song : songs) {
                artists[j].releaseSongs(song);
            }

           
            artists[j].performConcert();

            System.out.println("\nFinal Artist Details:");
            System.out.println("Name: " + artists[j].getName() + ", Fan Base: " + artists[j].getFanBase() + ", Money: $" + artists[j].getMoney());
        }

      
        Artist.displayTotalStats();

        scanner.close();
    }
}
