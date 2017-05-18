package com.example.episodic.episodes;

import com.example.episodic.shows.Shows;
import com.example.episodic.shows.ShowsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trainer3 on 5/18/17.
 */
@Service
public class EpisodeService {

    private final EpisodesRepository episodesRepository;
    private final ShowsRepository showsRepository;

    public EpisodeService(EpisodesRepository episodesRepository, ShowsRepository showsRepository) {
        this.episodesRepository = episodesRepository;
        this.showsRepository = showsRepository;
    }

    public EpisodeShow insertEpisodes(Long id, Episodes episodes){
        EpisodeShow episodeShow = new EpisodeShow();
        Shows shows = showsRepository.findById(id);

        episodes.setShows(shows);
        Episodes episodes1 = this.episodesRepository.save(episodes);

        episodeShow.setId(episodes1.getId());
        episodeShow.setEpisodeNumber(episodes1.getEpisodeNumber());
        episodeShow.setSeasonNumber(episodes1.getSeasonNumber());
        episodeShow.setTitle(episodeShow.getTitle());

        return episodeShow;
    }

    public List<EpisodeShow> findAllEpisodes(Long id) {
        List<EpisodeShow> episodeShowList = new ArrayList<>();
        Shows shows = showsRepository.findById(id);
        List<Episodes> episodes = episodesRepository.findAllByShowId(shows.getId());

        for(Episodes e : episodes){
            EpisodeShow episodeShow = new EpisodeShow();
            episodeShow.setId(e.getId());
            episodeShow.setEpisodeNumber(e.getEpisodeNumber());
            episodeShow.setSeasonNumber(e.getSeasonNumber());
            episodeShow.setTitle(episodeShow.getTitle());
            episodeShowList.add(episodeShow);
        }

        return episodeShowList;
    }
}
