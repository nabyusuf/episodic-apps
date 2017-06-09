package com.example.episodic.shows;

import com.example.episodic.MyTestBaseClass;
import com.example.episodic.episodes.Episodes;
import com.example.episodic.episodes.EpisodesRepository;
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

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
/**
 * Created by trainer3 on 5/17/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ShowsControllerTest extends MyTestBaseClass{

    @Autowired
    ShowsRepository repository;

    @Autowired
    EpisodesRepository episodesRepository;

    @Autowired
    MockMvc mvc;

    private Shows showsInit;

    @Before
    public void setup(){
        repository.deleteAll();

        Shows shows = new Shows("Friends");
        showsInit = repository.save(shows);

        Episodes episodes1 = new Episodes(showsInit,1,2);
        Episodes episodes2 = new Episodes(showsInit,1,3);
        episodesRepository.save(episodes1);
        episodesRepository.save(episodes2);
    }

    @After
    public void destructor(){
        repository.deleteAll();
        episodesRepository.deleteAll();
    }

    @Test
    @Transactional
    @Rollback
    public void testPost() throws Exception {

        Long count = repository.count();

        Map<String, Object> payload = new HashMap<String, Object>(){
            {
                put("name", "Sense8");
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request = post("/shows")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.name", equalTo("Sense8")));

        assertThat(repository.count(), equalTo(count+1));
    }

    @Test
    @Transactional
    @Rollback
    public void testGet() throws Exception {
        testPost();

        MockHttpServletRequestBuilder request = get("/shows")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name", equalTo("Friends")))
                .andExpect(jsonPath("$.[1].name", equalTo("Sense8")));
    }

    @Test
    @Transactional
    @Rollback
    public void testPostEpisodes() throws Exception {

        Long count = episodesRepository.count();

        Map<String, Object> epPayload = new HashMap<String, Object>(){
            {
                put("seasonNumber", 1);
                put("episodeNumber", 2);
            }
        };

        ObjectMapper epMapper = new ObjectMapper();
        String epJson = epMapper.writeValueAsString(epPayload);

        MockHttpServletRequestBuilder request = post("/shows/{id}/episodes", showsInit.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(epJson);

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.title", equalTo("S1 E2")));

        assertThat(episodesRepository.count(), equalTo(count+1));
    }

    @Test
    @Transactional
    @Rollback
    public void testGetEpisodes() throws Exception {

        MockHttpServletRequestBuilder request = get("/shows/{id}/episodes", showsInit.getId())
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title", equalTo("S1 E2")))
                .andExpect(jsonPath("$.[1].title", equalTo("S1 E3")));
    }

}
