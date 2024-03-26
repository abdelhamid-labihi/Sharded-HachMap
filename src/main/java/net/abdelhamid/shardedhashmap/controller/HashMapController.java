package net.abdelhamid.shardedhashmap.controller;

import net.abdelhamid.shardedhashmap.model.KeyValuePair;
import net.abdelhamid.shardedhashmap.service.HashMapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HashMapController {
    private final HashMapService hashMapService;

    public HashMapController(HashMapService hashMapService) {
        this.hashMapService = hashMapService;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody KeyValuePair pair) {
        hashMapService.add(pair);
        return ResponseEntity.ok("Added successfully");
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> retrieve(@PathVariable String key) {
        String value = hashMapService.retrieve(key);
        return value != null ? ResponseEntity.ok(value) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<String> delete(@PathVariable String key) {
        hashMapService.delete(key);
        return ResponseEntity.ok("Deleted successfully");
    }
}