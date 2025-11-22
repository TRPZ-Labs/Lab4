package ua.kpi.iasa.onlineradio.models;

import ua.kpi.iasa.onlineradio.models.iterators.ITrackIterator;
import ua.kpi.iasa.onlineradio.models.iterators.InfinitePlaylistIterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private List<Track> tracks;

    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
        this.tracks = new ArrayList<>();
    }


    public ITrackIterator createIterator() {

        return new InfinitePlaylistIterator(this.tracks);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Track> getTracks() {
        return Collections.unmodifiableList(tracks);
    }

    public void addTrack(Track track) {
        if (track != null && !tracks.contains(track)) {
            tracks.add(track);
        }
    }

    public void removeTrack(Track track) {
        tracks.remove(track);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", trackCount=" + tracks.size() +
                '}';
    }
}