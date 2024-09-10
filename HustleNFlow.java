import java.util.Scanner;

class Artist {
    private String name;
    private int fanBase;
    private double money;

    public Artist(String name, int fanBase, double money) {
        this.name = name;
        this.fanBase = fanBase;
        this.money = money;
    }
    public void releaseSongs(Song[] songs) {
        for (Song song : songs) {
            int popularity = song.calculatePopularity();
            fanBase += popularity * 10;
            money += popularity * 50;
            System.out.println(name + " released '" + song.getTitle() + "' and gained " + (popularity * 10) + " fans!");
        }
    }

    public void performConcert() {
        int concertFans = 50;
        fanBase += concertFans;
        money += 1000;
        System.out.println(name + " performed at a concert and gained " + concertFans + " fans!");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFanBase(int fanBase) {
        this.fanBase = fanBase;
    }

    public void setMoney(double money) {
        this.money = money;
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

    public int calculatePopularity() {
        return quality * 10;
    }

    public void displayDetails() {
        System.out.println("Title: " + title + ", Style: " + style + ", Quality: " + quality);
    }

    public String getTitle() {
        return title;
    }

    public String getStyle() {
        return style;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();

        System.out.print("Enter initial fan base: ");
        int initialFanBase = scanner.nextInt();

        System.out.print("Enter initial money: ");
        double initialMoney = scanner.nextDouble();

        Artist artist = new Artist(artistName, initialFanBase, initialMoney);

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
        System.out.println("Name: " + artist.getName() + ", Fan Base: " + artist.getFanBase() + ", Money: $" + artist.getMoney());

        artist.releaseSongs(songs); 

        artist.performConcert();

        System.out.println("\nFinal Artist Details:");
        System.out.println("Name: " + artist.getName() + ", Fan Base: " + artist.getFanBase() + ", Money: $" + artist.getMoney());

        scanner.close();
    }
}
