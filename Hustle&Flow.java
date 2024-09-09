class Artist {
    private String name;
    private int fanBase;
    private double money;

    
    public Artist(String name, int fanBase, double money) {
        this.name = name;      
        this.fanBase = fanBase; 
        this.money = money;     
    }

    
    public void releaseSong(Song song) {
        int popularity = song.calculatePopularity();
        fanBase += popularity * 10;
        money += popularity * 50;
        System.out.println(name + " released '" + song.getTitle() + "' and gained " + (popularity * 10) + " fans!");
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

    // Display song details
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
     
        Artist artist = new Artist("John Doe", 100, 500.0);
        Song song = new Song("Melody", "Pop", 8);

        System.out.println("Initial Artist Details:");
        System.out.println("Name: " + artist.getName() + ", Fan Base: " + artist.getFanBase() + ", Money: $" + artist.getMoney());

       
        artist.releaseSong(song);

       
        artist.performConcert();

        System.out.println("Final Artist Details:");
        System.out.println("Name: " + artist.getName() + ", Fan Base: " + artist.getFanBase() + ", Money: $" + artist.getMoney());
    }
}
