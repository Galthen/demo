package com.example.sse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/sse")
public class SseController {

    private static final Logger log = LoggerFactory.getLogger(SseController.class);

    // 创建一个线程池模拟异步处理逻辑
    private final ExecutorService executor = Executors.newCachedThreadPool();

    @GetMapping(value = "/mock", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter mockSse() {

        SseEmitter emitter = new SseEmitter(30_000L);
        String sessionId = "user-test-001";

        log.info("SSE 连接已建立: {}", sessionId);

        emitter.onCompletion(() -> log.info("连接已关闭: {}", sessionId));
        emitter.onTimeout(() -> log.warn("连接超时: {}", sessionId));
        emitter.onError((e) -> log.error("连接异常: {}", sessionId, e));

        executor.execute(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    String content = "这是模拟的第 " + i + " 条消息";

                    emitter.send(SseEmitter.event()
                            .id(String.valueOf(i))
                            .name("message")
                            .data(content));

                    log.info("已发送数据: {}", content);

                    Thread.sleep(1000);
                }

                emitter.send(SseEmitter.event().name("stop").data("EOF"));
                emitter.complete();

            } catch (IOException | InterruptedException e) {
                log.error("推送数据失败，连接可能已断开");
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }
}