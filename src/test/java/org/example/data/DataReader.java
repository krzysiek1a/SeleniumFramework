package org.example.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public List<HashMap<String, String>> getJsonDataToMap(String jsonPath) {
        String jsonContent = null;
        List<HashMap<String, String>> data;
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonContent = FileUtils.readFileToString(new File(jsonPath), StandardCharsets.UTF_8);
            data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
