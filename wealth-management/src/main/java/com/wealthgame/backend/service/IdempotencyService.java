package com.wealthgame.backend.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IdempotencyService {
    private final Map<String, Object> processedKeys = new ConcurrentHashMap<>();

    public boolean isProcessed(String key)
    {
        return processedKeys.containsKey(key);
    }
    public void marksAsProcessed(String key, Object response)
    {
        processedKeys.put(key,response);
    }
    public Object getResponse(String key)
    {
        return processedKeys.get(key);
    }
}
