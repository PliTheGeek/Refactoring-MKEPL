package Assignment;

public enum Genre {
    UNDEFINED,
    POP,
    ROCK,
    HIP_HOP,
    RNB,
    JAZZ,
    INSTRUMENTALS,
    CLOWNCORE
}

public enum DetailLevel {
    SONG_ONLY,
    WITH_ARTIST,
    WITH_ALBUM,
    FULL_DETAILS
}

public class Song {
    // Immutable fields
    private final String id;
    private final String title;
    private final String releaseYear;
    private final String musicFileURL;
    
    // Mutable fields
    private Genre genre;
    private Artist artist;
    private Album album;

    /**
     * Inner class to represent Artist information
     */
    private static class Artist {
        private final String name;
        private final String alias;
        private final String imageURL;

        public Artist(String name, String alias, String imageURL) {
            this.name = name;
            this.alias = alias;
            this.imageURL = imageURL;
        }
    }

    /**
     * Inner class to represent Album information
     */
    private static class Album {
        private final String name;
        private final String coverURL;

        public Album(String name, String coverURL) {
            this.name = name;
            this.coverURL = coverURL;
        }
    }

    /**
     * Constructor for Song
     */
    public Song(String id, String title, String releaseYear, String musicFileURL) {
        if (id == null || title == null || releaseYear == null || musicFileURL == null) {
            throw new IllegalArgumentException("All parameters must be non-null");
        }
        
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.musicFileURL = musicFileURL;
        this.genre = Genre.UNDEFINED;
    }

    /**
     * Sets the album information for the song
     */
    public void setAlbum(String albumName, String albumCoverURL) {
        if (albumName == null || albumCoverURL == null) {
            throw new IllegalArgumentException("Album name and cover URL must be non-null");
        }
        this.album = new Album(albumName, albumCoverURL);
    }

    /**
     * Sets the artist information for the song
     */
    public void setArtist(String artistName, String artistAlias, String artistImageURL) {
        if (artistName == null || artistAlias == null || artistImageURL == null) {
            throw new IllegalArgumentException("Artist details must be non-null");
        }
        this.artist = new Artist(artistName, artistAlias, artistImageURL);
    }

    /**
     * Sets the genre of the song
     */
    public void setGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be null");
        }
        this.genre = genre;
    }

    /**
     * Prints the basic song information
     */
    private void printBasicInfo() {
        StringBuilder info = new StringBuilder()
            .append("song title: ").append(title).append("\n")
            .append("release year: ").append(releaseYear);
        
        if (genre != Genre.UNDEFINED) {
            info.append("\ngenre: ").append(genre);
        }
        
        System.out.println(info.toString());
    }

    /**
     * Prints the artist information if available
     */
    private void printArtistInfo() {
        if (artist != null) {
            if (!artist.name.trim().isEmpty()) {
                System.out.println("artist name: " + artist.name);
            }
            if (!artist.alias.trim().isEmpty()) {
                System.out.println("artist also known as: " + artist.alias);
            }
        }
    }

    /**
     * Prints the album information if available
     */
    private void printAlbumInfo() {
        if (album != null && !album.name.trim().isEmpty()) {
            System.out.println("album title: " + album.name);
        }
    }

    /**
     * Prints song information based on the specified detail level
     */
    public void printInfo(DetailLevel level) {
        if (level == null) {
            throw new IllegalArgumentException("Detail level cannot be null");
        }

        printBasicInfo();
        
        switch (level) {
            case SONG_ONLY:
                break;
            case WITH_ARTIST:
                printArtistInfo();
                break;
            case WITH_ALBUM:
                printAlbumInfo();
                break;
            case FULL_DETAILS:
                printArtistInfo();
                printAlbumInfo();
                break;
        }
    }
}

Song song = new Song("1", "My Song", "2024", "http://example.com/song.mp3");
song.setGenre(Genre.POP);
song.setArtist("John Doe", "JD", "http://example.com/jd.jpg");
song.setAlbum("First Album", "http://example.com/album.jpg");
song.printInfo(DetailLevel.FULL_DETAILS);