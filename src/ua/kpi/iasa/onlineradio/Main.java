package ua.kpi.iasa.onlineradio;

import ua.kpi.iasa.onlineradio.data.*;
import ua.kpi.iasa.onlineradio.models.*;
import ua.kpi.iasa.onlineradio.repositories.*;
import ua.kpi.iasa.onlineradio.ui.LoginForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        System.out.println("--- Запуск Online Radio System ---");

        ITrackRepository trackRepo = new TrackRepository();
        IUserRepository userRepo = new UserRepository();
        IPlaylistRepository playlistRepo = new PlaylistRepository();

        MusicLibrary library = new MusicLibrary(trackRepo);
        Streamer streamer = new Streamer();

        setupTestData(trackRepo, userRepo, playlistRepo);

        playlistRepo.findById(1).ifPresent(streamer::setActivePlaylist);

        SwingUtilities.invokeLater(() -> {

            new LoginForm(userRepo, library, streamer).setVisible(true);
        });
    }

    private static void setupTestData(ITrackRepository tRepo, IUserRepository uRepo, IPlaylistRepository pRepo) {

        uRepo.save(new Administrator(0, "admin", "admin")); // логін: admin, пароль: admin
        uRepo.save(new User(0, "listener", "1234"));        // логін: listener, пароль: 1234

        // Треки
        Track t1 = new Track(0, "Bohemian Rhapsody", "Queen", "music/queen.mp3");
        Track t2 = new Track(0, "Smells Like Teen Spirit", "Nirvana", "music/nirvana.mp3");
        Track t3 = new Track(0, "Shape of You", "Ed Sheeran", "music/ed.mp3");
        Track t4 = new Track(0, "Believer", "Imagine Dragons", "music/believer.mp3");

        tRepo.save(t1);
        tRepo.save(t2);
        tRepo.save(t3);
        tRepo.save(t4);

        // Плейлист
        Playlist p1 = new Playlist(0, "Best Rock");
        p1.addTrack(t1);
        p1.addTrack(t2);
        pRepo.save(p1);

        System.out.println("Тестові дані завантажено.");
    }
}