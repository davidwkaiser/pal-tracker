package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {


    public String port;
    public String memoryLimit;
    public String cfInstanceIndex;
    public String cfInstanceAddress;

    public EnvController(
            @Value("${PORT:NOT SET}") String port,
            @Value("${MEMORY_LIMIT:NOT SET}") String memoryLimit,
            @Value("${CF_INSTANCE_INDEX:NOT SET}") String cfInstanceIndex,
            @Value("${CF_INSTANCE_ADDR:NOT SET}") String cfInstanceAddress) {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddress = cfInstanceAddress;
    }

    ;


    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("PORT", port);
        map.put("MEMORY_LIMIT", memoryLimit);
        map.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        map.put("CF_INSTANCE_ADDR", cfInstanceAddress);
        return map;


    }

}