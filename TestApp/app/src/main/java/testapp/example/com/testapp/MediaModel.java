package testapp.example.com.testapp;

public class MediaModel {
    private int mediaType;
    private String albumTitle;
    private String albumDescription;
    private String releaseDate;
    private int albumImage;

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(int albumImage) {
        this.albumImage = albumImage;
    }
}
