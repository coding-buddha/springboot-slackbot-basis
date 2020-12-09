package edu.pasudo123.study.demo.config.task;

import com.hubspot.algebra.Result;
import com.hubspot.slack.client.SlackClient;
import com.hubspot.slack.client.methods.params.chat.ChatPostMessageParams;
import com.hubspot.slack.client.models.response.SlackError;
import com.hubspot.slack.client.models.response.chat.ChatPostMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageTask {

    @Value("${slack.channelId}")
    private String channelId;

    private final SlackClient slackClient;

    @Scheduled(fixedRate = 10000L)
    public void postMessageByScheduling() {
        log.info("post message : {}", LocalDateTime.now());

        Result<ChatPostMessageResponse, SlackError> postResult = slackClient.postMessage(
                ChatPostMessageParams.builder()
                        .setText("Hello me! Here's a slack message! " + LocalDateTime.now().toString())
                        .setChannelId(channelId)
                        .build()
        ).join();

//        postResult.unwrapErrOrElseThrow();
    }
}
