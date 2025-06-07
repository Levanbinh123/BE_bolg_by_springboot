package com.example.bolg_BE.service;

public interface EmailService {
    public void sendMessage(String from, String to, String subject, String text);

}
