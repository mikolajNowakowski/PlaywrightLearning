package com.app.utils.impl.csv;

import com.app.utils.Loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvLoader implements Loader<List<Map<String,String>>> {
    @Override
    public List<Map<String, String>> load(String path) {
        List<Map<String, String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            String headerLine = br.readLine();
            if (headerLine == null) {
                return data;
            }
            String[] headers = headerLine.split(",");

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> rowMap = new HashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    if (i < values.length && !values[i].isEmpty()) {
                        rowMap.put(headers[i], values[i]);
                    } else {
                        rowMap.put(headers[i], null);
                    }
                }
                data.add(rowMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
