package com.example.rms_microservice.task;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.rms_microservice.model.Songs;
import com.example.rms_microservice.model.Streams;
import com.example.rms_microservice.repository.SongRepository;
import com.example.rms_microservice.service.StreamService;

@Component
public class StreamTask {

	  @Autowired
	    private SongRepository songRepository;

	    @Autowired
	    private StreamService streamService;

	    private Random random = new Random();

	    @Scheduled(cron = "0 0 * * * ?")
	    public void generateStreamData() {
	        LocalDate latestDate = streamService.findLatestStreamDate();
	        List<Songs> songs = songRepository.findAll();
	        
	        for (Songs song : songs) {
	            Long songId = song.getSongId();
	            Streams latestStream = streamService.findLatestStreamBySongId(songId).orElse(null);

	            LocalDate newDate = (latestStream == null) ? latestDate.plusDays(1) : latestStream.getDate().plusDays(1);

	            long randomStreams = random.nextInt(100000);
	            double royalty = streamService.calculateRoyalty(randomStreams);

	            Streams newStream = new Streams();
	            newStream.setDate(newDate);
	            newStream.setSongId(songId);
	            newStream.setStreams(randomStreams);
	            newStream.setRoyalty(royalty);

	            streamService.saveStream(newStream);
	        }
    }
}

