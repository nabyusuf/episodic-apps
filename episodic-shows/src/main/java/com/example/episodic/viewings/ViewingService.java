package com.example.episodic.viewings;

import com.example.episodic.episodes.EpisodeService;
import com.example.episodic.episodes.EpisodeShow;
import com.example.episodic.episodes.Episodes;
import com.example.episodic.episodes.EpisodesRepository;
import com.example.episodic.rabbitmq.ShowMessage;
import com.example.episodic.shows.Shows;
import com.example.episodic.shows.ShowsRepository;
import com.example.episodic.users.Users;
import com.example.episodic.users.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trainer3 on 5/19/17.
 */
@Service
public class ViewingService {

    private final ViewingRepository viewingRepository;
    private final EpisodesRepository episodesRepository;
    private final UsersRepository usersRepository;
    private final ShowsRepository showsRepository;
    private final EpisodeService service;

    public ViewingService(ViewingRepository viewingRepository, EpisodesRepository episodesRepository, UsersRepository usersRepository, ShowsRepository showsRepository, EpisodeService service) {
        this.viewingRepository = viewingRepository;
        this.episodesRepository = episodesRepository;
        this.usersRepository = usersRepository;
        this.showsRepository = showsRepository;
        this.service = service;
    }

    public HttpStatus patchViewing(Long id, EpisodeTracker episodeTracker) {
        Viewing viewing = new Viewing();
        Users user = this.usersRepository.findOne(id);
        Episodes episode = this.episodesRepository.findOne(episodeTracker.getEpisodeId());
        Shows show = this.showsRepository.findById(episode.getShows().getId());

        viewing.setUser(user);
        viewing.setEpisode(episode);
        viewing.setShow(show);
        viewing.setTimecode(episodeTracker.getTimecode());
        viewing.setUpdatedAt(episodeTracker.getUpdatedAt());

        this.viewingRepository.save(viewing);

        return HttpStatus.OK;
    }

    public List<RecentlyWatched> getRecentlyWatched(Long id) {
        List<Viewing> viewingList = viewingRepository.findAllByUsers(id);
        List<RecentlyWatched> recentlyWatchedList = new ArrayList<>();

        for(Viewing v : viewingList){
            Episodes episode = v.getEpisode();
            Shows show = v.getShow();
            EpisodeShow episodeShow = new EpisodeShow();
            episodeShow.setId(episode.getId());
            episodeShow.setEpisodeNumber(episode.getEpisodeNumber());
            episodeShow.setSeasonNumber(episode.getSeasonNumber());
            RecentlyWatched recentlyWatched = new RecentlyWatched();
            recentlyWatched.setEpisode(episodeShow);
            recentlyWatched.setShow(show);
            recentlyWatched.setTimecode(v.getTimecode());
            recentlyWatched.setUpdatedAt(v.getUpdatedAt());
            recentlyWatchedList.add(recentlyWatched);
        }

        return recentlyWatchedList;
    }

    public void findViewingByEpisodeId(ShowMessage message){
        Viewing viewing1 = new Viewing();

        Users user = usersRepository.findOne(message.getUserId());
        if(user == null) return;

        Episodes episode = episodesRepository.findOne(message.getEpisodeId());
        if(episode == null) return;

        Shows show = episode.getShows();
        if(show == null) return;

        Viewing viewing = viewingRepository.findAllByEpisodeAndUser(message.getEpisodeId(), message.getUserId());

        if(viewing == null){
            viewing1.setUser(user);
            viewing1.setEpisode(episode);
            viewing1.setShow(show);
            viewing1.setUpdatedAt(message.getCreatedAt());
            viewing1.setTimecode(message.getOffset());
            viewingRepository.save(viewing1);
        }
        else {
            viewing.setUpdatedAt(message.getCreatedAt());
            viewing.setTimecode(message.getOffset());
            viewingRepository.save(viewing);
        }
    }
}
