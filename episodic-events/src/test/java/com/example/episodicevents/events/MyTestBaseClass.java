package com.example.episodicevents.events;

import org.junit.AfterClass;
import org.junit.ClassRule;
import org.springframework.amqp.rabbit.junit.BrokerRunning;

/**
 * Created by trainer3 on 5/24/17.
 */
public class MyTestBaseClass {

    @ClassRule
    public static BrokerRunning brokerRunning = BrokerRunning.isRunningWithEmptyQueues("episodic-progress");

    @AfterClass
    public static void tearDown() {
        brokerRunning.removeTestQueues();
    }

}
