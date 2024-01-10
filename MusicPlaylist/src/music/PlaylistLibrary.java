package music;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.w3c.dom.Node;

public class PlaylistLibrary {

    private ArrayList<Playlist> songLibrary; 

    public PlaylistLibrary(ArrayList<Playlist> songLibrary) {
        this.songLibrary = songLibrary;
    }

    
    public PlaylistLibrary() {
        this(null);
    }

    public Playlist createPlaylist(String filename) {

        Playlist playlist = new Playlist();
        StdIn.setFile(filename);
        SongNode songNode = new SongNode();
        playlist.setLast(songNode);
        playlist.setSize(0);
        int counter = 0;

        if(StdIn.isEmpty())
        {
            return new Playlist(null, 0);
        }
        
        while(StdIn.hasNextLine())
        {
            String[] data = StdIn.readLine().split(",");
            String name = data[0];
            String artist = data[1];
            int year = Integer.parseInt(data[2]);
            int pop = Integer.parseInt(data[3]);
            String link = data[4];

            //4.2
            Song newSong = new Song(name, artist, year, pop, link);

            //4.3 & 4.4
            if(playlist.getSize() == 0)
            {
                songNode.setSong(newSong);
                playlist.setLast(songNode);
                songNode.setNext(songNode);
                playlist.setSize(playlist.getSize()+1);   
            }
            else{
                SongNode newNode = new SongNode();
                newNode.setSong(newSong);
                newNode.setNext(songNode);
                playlist.getLast().setNext(newNode);
                playlist.setLast(newNode);
                playlist.setSize(playlist.getSize()+1);
            }
            counter++;
        }

        if(counter==0)
        {
            return new Playlist(null, 0);
        }
        return playlist; 
    }

    
    public void addPlaylist(String filename, int playlistIndex) {
        if ( songLibrary == null ) {
            songLibrary = new ArrayList<Playlist>();
        }
        if ( playlistIndex >= songLibrary.size() ) {
            songLibrary.add(createPlaylist(filename));
        } else {
            songLibrary.add(playlistIndex, createPlaylist(filename));
        }        
    }

    
    public boolean removePlaylist(int playlistIndex) {
        if ( songLibrary == null || playlistIndex >= songLibrary.size() ) {
            return false;
        }

        songLibrary.remove(playlistIndex);
            
        return true;
    }
    
    public void addAllPlaylists(String[] filenames) {
        ArrayList<Playlist> songLibray = new ArrayList<Playlist>();
        for(int i=0; i<filenames.length; i++)
        {
            addPlaylist(filenames[i], i);
        }
    }

    
    public boolean insertSong(int playlistIndex, int position, Song song) {
        Playlist edit = songLibrary.get(playlistIndex);
        SongNode insertNode = new SongNode(song, null);
        
        if(edit.getSize()==0 && position !=1)
        {
            return false;
        }
        else if(edit.getSize()==0 && position ==1)
        {
            edit.setLast(insertNode);
            edit.getLast().setNext(insertNode);
            edit.setSize(1);
            return true;
        }
        
        SongNode ptr = edit.getLast();
        SongNode pre = null;
        int counter = 0;

        if(edit.getLast()!=null)
        {
            do{
                if(position == edit.getSize()+1)
                {
                    insertNode.setNext(ptr.getNext());
                    ptr.setNext(insertNode);
                    edit.setLast(insertNode);
                    edit.setSize(edit.getSize()+1);
                    return true;
                }
                if(counter == position)
                {
                    pre.setNext(insertNode);
                    insertNode.setNext(ptr);
                    edit.setSize(edit.getSize()+1);
                    return true;
                }
                pre= ptr;
                ptr = ptr.getNext();
                counter++;
            }while(ptr!=edit.getLast());
        }
        return false;
        
    }

    public boolean removeSong(int playlistIndex, Song song) {
     
        Playlist edit = songLibrary.get(playlistIndex);
        SongNode ptr = edit.getLast();
        SongNode pre = null;
        if(edit.getSize() ==1 && song.equals(edit.getLast().getSong()))
        {
            edit.setLast(null);
            edit.setSize(0);
            return true;
        }

        if(edit.getSize()!=0)
        {
            do{
                if(ptr.getSong().equals(song) && pre!=null)
                {
                    pre.setNext(ptr.getNext());
                    edit.setSize(edit.getSize()-1);
                    return true;
                }
                pre= ptr;
                ptr = ptr.getNext();
            }while(ptr!=edit.getLast());
            if(edit.getLast().getSong().equals(song))
            {
                pre.setNext(edit.getLast().getNext());
                edit.setLast(pre);
                edit.setSize(edit.getSize()-1);
                return true;
            }
        }
        return false;
    }

   
    public void reversePlaylist(int playlistIndex) {
        // WRITE YOUR CODE HERE
        Playlist edit = songLibrary.get(playlistIndex);
        if(edit.getSize()==0)
        {
            return;
        }
        SongNode newLast = edit.getLast().getNext();
        SongNode ptr = newLast;
        SongNode prev = edit.getLast();
        SongNode next = newLast.getNext();

        if(edit == null)
        {return;}
        do{
            ptr.setNext(prev);
            prev = ptr;
            ptr = next;
            next=next.getNext();
        }while(ptr!=newLast);
        songLibrary.get(playlistIndex).setLast(newLast);
    }

    
    public void mergePlaylists(int playlistIndex1, int playlistIndex2) {
        int lower = playlistIndex1;
        int upper = playlistIndex2;
        if(lower > upper)
        {
            lower = playlistIndex2;
            upper = playlistIndex1;   
        }

        Playlist list1 = songLibrary.get(lower);
        Playlist list2 = songLibrary.get(upper);
        if(list1.getLast()==null || list1.getSize() == 0)
        {
            SongNode last = list2.getLast();
            int size = list2.getSize();
            list1.setLast(last);
            list1.setSize(size);
            removePlaylist(upper);
            return;
        }
        if(list2.getLast()==null)
        {
            removePlaylist(upper);
            return;
        }

        SongNode head1 = list1.getLast().getNext();
        int size1 = list1.getSize();
        
        SongNode head2 = list2.getLast().getNext();
        int size2 = list2.getSize();

        SongNode newHead = new SongNode();
        Playlist merged = new Playlist();
        SongNode ptr = new SongNode();

        while(list1.getSize() != 0 && list2.getSize() != 0)
        {
            if(head1.getSong().getPopularity()>=head2.getSong().getPopularity())
            {
                if(merged.getLast() == null)
                {
                    newHead = head1;
                    merged.setLast(head1);
                    ptr = merged.getLast();
                    removeSong(lower, head1.getSong());
                    head1=head1.getNext();
                }
                else{
                    ptr.setNext(head1);
                    ptr=ptr.getNext();
                    removeSong(lower, head1.getSong());
                    head1 = head1.getNext();
                }
            }
            else if(head1.getSong().getPopularity()<head2.getSong().getPopularity())
            {
                if(merged.getLast() == null)
                {
                    newHead = head2;
                    merged.setLast(head2);
                    ptr = merged.getLast();
                    removeSong(upper, head2.getSong());
                    head2=head2.getNext();
                }
                else{
                    ptr.setNext(head2);
                    ptr=ptr.getNext();
                    removeSong(upper, head2.getSong());
                    head2 = head2.getNext();
                }
            }
        }
        if(songLibrary.get(lower).getSize() == 0)
        {
            ptr.setNext(list2.getLast().getNext());
            ptr = list2.getLast();
        }
        if(songLibrary.get(upper).getSize() == 0)
        {
            ptr.setNext(list1.getLast().getNext());
            ptr = list1.getLast();
        }
        merged.setLast(ptr);
        merged.getLast().setNext(newHead);
        merged.setSize(size1 + size2);
        songLibrary.set(lower, merged);
        removePlaylist(upper);
    }

    
    public void shufflePlaylist(int playlistIndex) {
        Playlist ogList = songLibrary.get(playlistIndex);
        if(ogList.getSize()==0)
        {
            return;
        }
        SongNode ptr = ogList.getLast().getNext();
        Playlist shuffledList = new Playlist();
        songLibrary.add(songLibrary.size(), shuffledList);
        
        while(ogList.getSize()!=0)
        {
            int random = StdRandom.uniformInt(ogList.getSize()+1);
            for(int i=0; i<random; i++)
            {
                ptr = ptr.getNext();
            }
            SongNode newNode = ptr;
            removeSong(playlistIndex, newNode.getSong());
            if(shuffledList.getSize()==0)
            {
                shuffledList.setLast(ptr);
                ptr.setNext(ptr);
                shuffledList.setSize(shuffledList.getSize()+1);
            }
            else
            {
                insertSong(songLibrary.size()-1, shuffledList.getSize()+1, newNode.getSong());
            }
            ptr = ogList.getLast().getNext();
        }
        songLibrary.set(playlistIndex, shuffledList);
        songLibrary.remove(songLibrary.size()-1);
    }


   
    public void playPlaylist(int playlistIndex, int repeats) {
        final String NO_SONG_MSG = " has no link to a song! Playing next...";
        if (songLibrary.get(playlistIndex).getLast() == null) {
            StdOut.println("Nothing to play.");
            return;
        }

        SongNode ptr = songLibrary.get(playlistIndex).getLast().getNext(), first = ptr;

        do {
            StdOut.print("\r" + ptr.getSong().toString());
            if (ptr.getSong().getLink() != null) {
                StdAudio.play(ptr.getSong().getLink());
                for (int ii = 0; ii < ptr.getSong().toString().length(); ii++)
                    StdOut.print("\b \b");
            }
            else {
                StdOut.print(NO_SONG_MSG);
                try {
                    Thread.sleep(2000);
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
                for (int ii = 0; ii < NO_SONG_MSG.length(); ii++)
                    StdOut.print("\b \b");
            }

            ptr = ptr.getNext();
            if (ptr == first) repeats--;
        } while (ptr != first || repeats > 0);
    }

    public void printPlaylist(int playlistIndex) {
        StdOut.printf("%nPlaylist at index %d (%d song(s)):%n", playlistIndex, songLibrary.get(playlistIndex).getSize());
        if (songLibrary.get(playlistIndex).getLast() == null) {
            StdOut.println("EMPTY");
            return;
        }
        SongNode ptr;
        for (ptr = songLibrary.get(playlistIndex).getLast().getNext(); ptr != songLibrary.get(playlistIndex).getLast(); ptr = ptr.getNext() ) {
            StdOut.print(ptr.getSong().toString() + " -> ");
        }
        if (ptr == songLibrary.get(playlistIndex).getLast()) {
            StdOut.print(songLibrary.get(playlistIndex).getLast().getSong().toString() + " - POINTS TO FRONT");
        }
        StdOut.println();
    }

    public void printLibrary() {
        if (songLibrary.size() == 0) {
            StdOut.println("\nYour library is empty!");
        } else {
                for (int ii = 0; ii < songLibrary.size(); ii++) {
                printPlaylist(ii);
            }
        }
    }

     public ArrayList<Playlist> getPlaylists() { return songLibrary; }
     public void setPlaylists(ArrayList<Playlist> p) { songLibrary = p; }
}
