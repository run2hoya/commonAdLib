package com.castis.adlib.util.idgenerator;

public interface EntityIdGenerator {
    String generateId() throws InvalidSystemClockException, GetHardwareIdFailedException;
}
