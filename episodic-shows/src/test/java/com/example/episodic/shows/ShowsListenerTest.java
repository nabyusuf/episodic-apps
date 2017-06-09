package com.example.episodic.shows;

import com.example.episodic.MyTestBaseClass;
import com.example.episodic.episodes.EpisodesRepository;
import com.example.episodic.users.UsersRepository;
import com.example.episodic.viewings.ViewingRepository;
import com.example.episodic.viewings.ViewingService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by trainer3 on 5/23/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShowsListenerTest extends MyTestBaseClass {
    @Autowired
    ViewingService service;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ShowsRepository showsRepository;
    @Autowired
    EpisodesRepository episodesRepository;
    @Autowired
    ViewingRepository viewingRepository;

}
