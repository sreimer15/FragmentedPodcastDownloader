package sreimerdevelopment.fragmentedpodcastdownloader;

/**
 * Created by sreim on 7/7/2016.
 */
public class Application {
    private String title;
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + "\n\n" +
                "Links: " + getLink() + "\n";
    }
}
