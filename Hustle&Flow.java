class Artist {
    private String name;
    private int fanBase;
    private double money;



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







    }
}