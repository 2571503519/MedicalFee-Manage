package com.jackaroo.spring_boot_demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JackarooZhang
 * @date 2018/5/10 23:47
 */
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /*@Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("Current time is " + dateFormat.format(new Date()));
    }*/

}
