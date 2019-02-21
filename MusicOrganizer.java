import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    
    //private ArrayList<Track> sideB;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;     
    // the index number of the track picked by java.util.random.
    private int randTrack;
    // acts as a reference hook for the random feature of java.util.random
    private Random ran;
    // boolean determines whether the copy of the tracks arraylist (sideB) has been shuffled for the shufflenorepeat feature.
    private boolean isShuffled;
    
    private int trackcount;
    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        tracks = new ArrayList<>();
        player = new MusicPlayer();
        reader = new TrackReader();
        ran = new Random();
        //isShuffled = false;
        readLibrary("../audio");
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
        //ShuffleInit();
        trackcount = tracks.size();
    }   
        
    private void ShuffleInit()
    {
       ArrayList sideB = (ArrayList) tracks.clone();
       if(isShuffled = false){
                Collections.shuffle(sideB);
                isShuffled = true;
            }
    }
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }
    
    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if(indexValid(index)) {
            Track track = tracks.get(index);
            player.startPlaying(track.getFilename());
            System.out.println("Now playing: " + track.getArtist() + 
            " - " + track.getTitle());
        }
    }
    
     /** 
     * Plays a random track
     */
    public void shuffleP()
    {
        int randTrack = ran.nextInt(tracks.size());
        // TODO add protection so someone cant mash shuffle and play several
        //songs on top of eachother.
        if(indexValid(randTrack)) {
            Track track = tracks.get(randTrack);
            player.startPlaying(track.getFilename());
            System.out.println("Now playing: " + track.getArtist() +
            " - " + track.getTitle());
        }
    }
    
    /**
     *  plays all tracks in random order.
     */    
    public void shufflenoRepeat()
    {
           int randTrack = ran.nextInt(tracks.size());
        // TODO add protection so someone cant mash shuffle and play several
        //songs on top of eachother.
        if(indexValid(randTrack)) {
            Track track = tracks.get(randTrack);
            player.startPlaying(track.getFilename());
            System.out.println("Now playing: " + track.getArtist() +
            " - " + track.getTitle());
            tracks.remove(randTrack);
        }
    }
    
    /**
     * a code slice used to test the implementation of the shuffle feature
     * should be set to private before final commit.
     */
    private int numberTest()
    {
        int randTrack = ran.nextInt(tracks.size());
        
        return randTrack;
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }
    
    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if(tracks.size() > 0) {
            player.startPlaying(tracks.get(0).getFilename());
        }
    }
    
    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }
}
