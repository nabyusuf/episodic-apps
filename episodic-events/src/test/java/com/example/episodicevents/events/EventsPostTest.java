package com.example.episodicevents.events;

import com.example.episodicevents.repo.EventsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer3 on 5/22/17.
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EventsPostTest extends MyTestBaseClass {

    @Autowired
    MockMvc mvc;

    @Autowired
    EventsRepository repository;

    @Before
    public void setUp(){
        repository.deleteAll();
    }

    @After
    public void kill(){
        repository.deleteAll();
    }

    @Test
    public void testPostPlayEvents() throws Exception {

        Long count = repository.count();

        String requestString = "{\"type\": \"play\",\"userId\": 52,\"showId\": 987,\"episodeId\": 456,\"createdAt\": \"2017-11-08T15:59:13.0091745\",\"data\": {\"offset\": 0}}";

        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestString);

        this.mvc.perform(request)
                .andExpect(status().isOk());

        assertThat(repository.count(), equalTo(count+1));
    }

    @Test
    public void testPostPauseEvents() throws Exception {

        Long count = repository.count();

        String requestString = "{\"type\": \"pause\",\"userId\": 52,\"showId\": 987,\"episodeId\": 456,\"createdAt\": \"2017-11-08T15:59:13.0091745\",\"data\": {\"offset\": 1023}}";

        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestString);

        this.mvc.perform(request)
                .andExpect(status().isOk());

        assertThat(repository.count(), equalTo(count+1));
    }

    @Test
    public void testPostFFEvents() throws Exception {

        Long count = repository.count();

        String requestString = "{\"type\": \"fastForward\",\"userId\": 52,\"showId\": 987,\"episodeId\": 456,\"createdAt\": \"2017-11-08T15:59:13.0091745\", \"data\": {\"startOffset\": 4,\"endOffset\": 408,\"speed\": 2.5}}";

        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestString);

        this.mvc.perform(request)
                .andExpect(status().isOk());

        assertThat(repository.count(), equalTo(count+1));
    }

    @Test
    public void testPostRewindEvents() throws Exception {

        Long count = repository.count();

        String requestString = "{\"type\": \"rewind\",\"userId\": 52,\"showId\": 987,\"episodeId\": 456,\"createdAt\": \"2017-11-08T15:59:13.0091745\",\"data\": {\"startOffset\": 4,\"endOffset\": 408,\"speed\": 2.5}}";

        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestString);

        this.mvc.perform(request)
                .andExpect(status().isOk());

        assertThat(repository.count(), equalTo(count+1));
    }

    @Test
    public void testPostProgressEvents() throws Exception {

        Long count = repository.count();

        String requestString = "{\"type\": \"progress\",\"userId\": 52,\"showId\": 987,\"episodeId\": 456,\"createdAt\": \"2017-11-08T15:59:13.0091745\",\"data\": {\"offset\": 4}}";

        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestString);

        this.mvc.perform(request)
                .andExpect(status().isOk());

        assertThat(repository.count(), equalTo(count+1));
    }

    @Test
    public void testPostScrubEvents() throws Exception {

        Long count = repository.count();

        String requestString = "{\"type\": \"scrub\",\"userId\": 52,\"showId\": 987,\"episodeId\": 456,\"createdAt\": \"2017-11-08T15:59:13.0091745\",\"data\": {\"startOffset\": 4,\"endOffset\": 408}}";

        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestString);

        this.mvc.perform(request)
                .andExpect(status().isOk());

        assertThat(repository.count(), equalTo(count+1));
    }

    @Test
    public void getEvent() throws Exception {
        MockHttpServletRequestBuilder request = get("/recent");

        this.mvc.perform(request)
                .andExpect(status().isOk());
    }
}
