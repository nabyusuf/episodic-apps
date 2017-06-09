package com.example.episodic.users;

import com.example.episodic.MyTestBaseClass;
import com.example.episodic.episodes.Episodes;
import com.example.episodic.episodes.EpisodesRepository;
import com.example.episodic.shows.Shows;
import com.example.episodic.shows.ShowsRepository;
import com.example.episodic.viewings.Viewing;
import com.example.episodic.viewings.ViewingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer3 on 5/17/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class UsersControllerTest extends MyTestBaseClass{
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ViewingRepository viewingRepository;

    @Autowired
    ShowsRepository showsRepository;

    @Autowired
    EpisodesRepository episodesRepository;

    @Autowired
    MockMvc mvc;

    private Users users;
    private Shows showsInit;
    private Episodes episodes1;
    private Episodes episodes2;
    private Episodes episodes3;
    private Viewing viewing1;
    private Viewing viewing2;
    private Viewing viewing3;

    @Before
    public void setup(){
        usersRepository.deleteAll();
        showsRepository.deleteAll();
        episodesRepository.deleteAll();
        viewingRepository.deleteAll();

        users = usersRepository.save(new Users("abc@mail.com"));

        Shows shows = new Shows("Friends");
        showsInit = showsRepository.save(shows);

        episodes1 = new Episodes(showsInit,1,2);
        episodes2 = new Episodes(showsInit,1,3);
        episodes3 = new Episodes(showsInit,1,4);
        episodesRepository.save(episodes1);
        episodesRepository.save(episodes2);
        episodesRepository.save(episodes3);

        viewing1 = new Viewing();
        viewing1.setUser(users);
        viewing1.setShow(showsInit);
        viewing1.setEpisode(episodes1);
        viewing1.setTimecode(79);
        viewing1.setUpdatedAt(new Date());

        viewing2 = new Viewing();
        viewing2.setUser(users);
        viewing2.setShow(showsInit);
        viewing2.setEpisode(episodes2);
        viewing2.setTimecode(49);
        viewing2.setUpdatedAt(new Date());

        viewing3 = new Viewing();
        viewing3.setUser(users);
        viewing3.setShow(showsInit);
        viewing3.setEpisode(episodes3);
        viewing3.setTimecode(99);
        viewing3.setUpdatedAt(new Date());

        viewingRepository.save(viewing1);
        viewingRepository.save(viewing2);
        viewingRepository.save(viewing3);
    }

    @After
    public void destructor(){
        usersRepository.deleteAll();
        showsRepository.deleteAll();
        episodesRepository.deleteAll();
        viewingRepository.deleteAll();
    }

    @Test
    @Transactional
    @Rollback
    public void testPost() throws Exception {

        Long count = usersRepository.count();

        Map<String, Object> payload = new HashMap<String, Object>(){
            {
                put("email", "joe@example.com");
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.email", equalTo("joe@example.com")));

        assertThat(usersRepository.count(), equalTo(count+1));
    }

    @Test
    @Transactional
    @Rollback
    public void testGet() throws Exception {
        testPost();

        MockHttpServletRequestBuilder request = get("/users")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].email", equalTo("abc@mail.com")))
                .andExpect(jsonPath("$.[1].email", equalTo("joe@example.com")));
    }

    @Test
    @Transactional
    @Rollback
    public void patchViewings() throws Exception {

        Map<String, Object> payload = new HashMap<String, Object>(){
            {
                put("episodeId", episodes1.getId());
                put("updatedAt", "2017-05-04T11:45:34.9182");
                put("timecode", 79);
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request = patch("/users/{id}/viewings",users.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void testGetRecentlyWatched() throws Exception {
        MockHttpServletRequestBuilder request = get("/users/{id}/recently-watched", users.getId());

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }
}
