package com.neo.account.service;

/**
 * @Author ABODE
 * @Date 2025/03/09 4:26 PM
 */
public interface NotificationService {
    void sendMessage(String topic, Object message);
}
