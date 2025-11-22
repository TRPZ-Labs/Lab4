package ua.kpi.iasa.onlineradio.models;

import ua.kpi.iasa.onlineradio.models.iterators.ITrackIterator;


public class Streamer {
    private Playlist activePlaylist;
    private Track currentTrack;
    private ITrackIterator trackIterator;

    public void setActivePlaylist(Playlist playlist) {
        this.activePlaylist = playlist;
        if (playlist != null) {
            this.trackIterator = playlist.createIterator();
        } else {
            this.trackIterator = null;
        }
        this.currentTrack = null;
        System.out.println("Активний плейлист змінено на: " + (playlist != null ? playlist.getName() : "null"));
    }

    public void play() {
        if (trackIterator == null || !trackIterator.hasNext()) {
            System.out.println("Помилка: плейлист не встановлено або він порожній.");
            return;
        }
        if (currentTrack == null) {
            nextTrack();
        } else {
            System.out.println("▶️ Відтворення продовжено: " + currentTrack.getArtist() + " - " + currentTrack.getTitle());
        }
    }

    public void nextTrack() {
        if (trackIterator == null) {
            System.out.println("Неможливо перемкнути трек: плейлист не активний.");
            return;
        }
        currentTrack = trackIterator.next();
        if (currentTrack != null) {
            System.out.println("🎧 Зараз в ефірі: " + currentTrack.getArtist() + " - " + currentTrack.getTitle());
        } else {
            System.out.println("Плейлист порожній.");
        }
    }

    public Track getCurrentTrack() {
        return currentTrack;
    }
}