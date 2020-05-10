package com.santander.processor.fake;

import com.santander.processor.event.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
public class RPCFake {

    ConcurrentHashMap<String, Document> documents = new ConcurrentHashMap<>();

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Document document) {

        if (documents.containsKey(document.getIdentifier())) {
            return ResponseEntity.status(409).body(document);
        }

        documents.put(document.getIdentifier(), document);
        return ResponseEntity.status(201).build();
    }
}
