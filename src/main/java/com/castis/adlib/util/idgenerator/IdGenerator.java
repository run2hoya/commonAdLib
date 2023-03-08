package com.castis.adlib.util.idgenerator;

import com.castis.adlib.util.DateUtil;

import java.util.Date;


public class IdGenerator implements EntityIdGenerator {

//   id format  =>
//   timestamp |datacenter | sequence

	private int datacenterId = 1;
    private final int maxDatacenterId = 10;
    private final long sequenceMax = 1000;
    private final int sleepTime = 100;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;
    private String lastTimeStr = null;

    private static IdGenerator idGenerator;

    public static IdGenerator getInstance() throws GetHardwareIdFailedException {
        if (idGenerator == null) idGenerator = new IdGenerator();
        return idGenerator;
    }
    
    public void setDatacenterId(int datacenterId) {
		this.datacenterId = datacenterId;
	}

	private IdGenerator() throws GetHardwareIdFailedException {
        if (datacenterId >= maxDatacenterId || datacenterId < 0) {
            throw new GetHardwareIdFailedException("Invalid DatacenterId");
        }
    }

    @Override
    public synchronized String generateId() throws InvalidSystemClockException {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new InvalidSystemClockException("Clock moved backwards.  Refusing to generate id for " + (
                    lastTimestamp - timestamp) + " milliseconds.");
        }
        
        String currentTimeStr = DateUtil.date2String(new Date(timestamp), "yyMMddHHmmss");
        if (lastTimeStr != null && lastTimeStr.equals(currentTimeStr)) {
            sequence = (sequence + 1) % sequenceMax;
            if (sequence == 0) {
            	currentTimeStr = untilNextSec(lastTimeStr);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        lastTimeStr = currentTimeStr;
        
        return lastTimeStr + datacenterId + String.format("%03d", sequence);
    }

    protected String untilNextSec(String lastTime) {
    	String timestamp = DateUtil.date2String(new Date(System.currentTimeMillis()), "yyMMddHHmmss");

        while (lastTime.equals(timestamp)) {
        	//sleep
        	try{Thread.sleep(sleepTime);}catch(Exception e){};
            timestamp = DateUtil.date2String(new Date(System.currentTimeMillis()), "yyMMddHHmmss");
        }
        return timestamp;
    }
}
