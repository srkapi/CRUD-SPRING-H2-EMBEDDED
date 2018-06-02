package com.srkapi.demo.controller;


import com.srkapi.demo.model.Book;
import com.srkapi.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

public class BookControllerRest {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List> getAll(){
        try{
            return new ResponseEntity<>(this.bookRepository.findAll(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @PostMapping
    public ResponseEntity<Book> saveBook(@Valid Book book){
        try{

            return new ResponseEntity<> (this.bookRepository.save(book),HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> findById(@PathVariable("id") long id){
        try{
            return new ResponseEntity(this.bookRepository.findById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("id") long id){
        try{
            this.bookRepository.deleteById(id);
            return new ResponseEntity( HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
