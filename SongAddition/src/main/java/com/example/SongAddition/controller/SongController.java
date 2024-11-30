package com.example.SongAddition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SongAddition.model.Songs;
import com.example.SongAddition.repo.SongRepository;
@RestController
@CrossOrigin(value = {"http://localhost:3000/","https://royalty-management-system-kdrb.vercel.app/"})
@RequestMapping("/api/songs")
public class SongController {

@Autowired
private SongRepository songRepository;

@PostMapping
public ResponseEntity<Songs> createSong(@RequestBody Songs song) {
 Songs savedSong = songRepository.save(song);
 System.out.println(song);
 return new ResponseEntity<>(savedSong, HttpStatus.CREATED);
}
}
