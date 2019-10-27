/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficvolume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author user
 */
public class Main {
    
    public static void main(String[] args) {
        String trainingFile = "train_80p100_38556.arff";
        String testingFile = "test_20p100_9638.arff";
        String predictingFile = "predict.arff";
        int clsIdx = 7;
        String modelFileName = "traffic_volume.model";
        
        LinearRegressionML lr = new LinearRegressionML();
        
//        lr.loadModel(modelFileName);
        lr.trainAndTest(trainingFile, testingFile, clsIdx);
//        lr.saveModel(modelFileName);
        
        UiApplication app = new UiApplication();
        app.setVisible(true);

        lr.predictDataSet(predictingFile, clsIdx);
        HashMap<String, Object> holidayMap = new HashMap<>();
        String holiday = "None";
        holidayMap.put("value", holiday);
        holidayMap.put("type", "String");
        
        HashMap<String, Object> tempMap = new HashMap<>();
        double temp = 287.58;
        tempMap.put("value", temp);
        tempMap.put("type", "double");
        
        HashMap<String, Object> rainMap = new HashMap<>();
        double rain1Hr = 0.0;
        rainMap.put("value", rain1Hr);
        rainMap.put("type", "double");
        
        HashMap<String, Object> snowMap = new HashMap<>();
        double snow1Hr = 0.0;
        snowMap.put("value", snow1Hr);
        snowMap.put("type", "double");
        
        HashMap<String, Object> cloudMap = new HashMap<>();
        double cloudsAll = 50.0;
        cloudMap.put("value", cloudsAll);
        cloudMap.put("type", "double");
        
        HashMap<String, Object> weatherDescMap = new HashMap<>();
        String weatherDescription = "overcast clouds"; 
        weatherDescMap.put("value", weatherDescription);
        weatherDescMap.put("type", "String");
        
        HashMap<String, Object> dayTimeMap = new HashMap<>();
        String dayTime = "Monday 12:00";
        dayTimeMap.put("value", dayTime);
        dayTimeMap.put("type", "String");
        
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        list.add(holidayMap);
        list.add(tempMap);
        list.add(rainMap);
        list.add(snowMap);
        list.add(cloudMap);
        list.add(weatherDescMap);
        list.add(dayTimeMap);
        
        double res = lr.predictOneInstance(predictingFile, clsIdx, list);
        System.out.println("Traffic Volume: " + (res >= 0.0? (int)res: 0));
    }
    
}
