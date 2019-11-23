/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficvolume;

import java.awt.Dimension;

/**
 *
 * @author user
 */
public class Main {
    
    public static void main(String[] args) {
        String trainingFile = "train_80p100_38555_convert_holiday_and_daytime.arff";
        String testingFile = "test_20p100_9638_convert_holiday_and_daytime.arff";
//        String predictingFile = "predict.arff";
        int clsIdx = 7;
        String modelFileName;
        UiApplication app;
        String mode = "nn";
        boolean loadModel = true;
        if(mode.equals("nn")){
            modelFileName = "traffic_volume_nn.model";
            NeuralNetworkML nn = new NeuralNetworkML();
            if(loadModel){
                nn.loadModel(modelFileName);
            }
            else{
                nn.trainAndTest(trainingFile, testingFile, clsIdx);
                nn.saveModel(modelFileName);
            }
            app = new UiApplication(nn, clsIdx);
        }
        else{
            modelFileName = "traffic_volume_lr.model";
            LinearRegressionML lr = new LinearRegressionML();
            if(loadModel){
                lr.loadModel(modelFileName);
            }
            else{
                lr.trainAndTest(trainingFile, testingFile, clsIdx);
                lr.saveModel(modelFileName);
            }
            app = new UiApplication(lr, clsIdx);
        }
        
        app.setPreferredSize(new Dimension(700,560));
        app.pack();
        app.setLocationRelativeTo(null);
        app.setResizable(false);
        app.setVisible(true);
//
//        lr.predictDataSet(predictingFile, clsIdx);
//        String holiday = "None";
//        HashMap<String, Object> holidayMap = new HashMap<>();
//        holidayMap.put("value", holiday);
//        holidayMap.put("type", "String");
//        
//        double temp = 287.58;
//        HashMap<String, Object> tempMap = new HashMap<>();
//        tempMap.put("value", temp);
//        tempMap.put("type", "double");
//        
//        double rain1Hr = 0.0;
//        HashMap<String, Object> rainMap = new HashMap<>();
//        rainMap.put("value", rain1Hr);
//        rainMap.put("type", "double");
//        
//        double snow1Hr = 0.0;
//        HashMap<String, Object> snowMap = new HashMap<>();
//        snowMap.put("value", snow1Hr);
//        snowMap.put("type", "double");
//        
//        double cloudsAll = 50.0;
//        HashMap<String, Object> cloudMap = new HashMap<>();
//        cloudMap.put("value", cloudsAll);
//        cloudMap.put("type", "double");
//        
//        String weatherDescription = "overcast clouds";
//        HashMap<String, Object> weatherDescMap = new HashMap<>();
//        weatherDescMap.put("value", weatherDescription);
//        weatherDescMap.put("type", "String");
//        
//        String dayTime = "Monday 12:00";
//        HashMap<String, Object> dayTimeMap = new HashMap<>();
//        dayTimeMap.put("value", dayTime);
//        dayTimeMap.put("type", "String");
//        
//        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
//        list.add(holidayMap);
//        list.add(tempMap);
//        list.add(rainMap);
//        list.add(snowMap);
//        list.add(cloudMap);
//        list.add(weatherDescMap);
//        list.add(dayTimeMap);
//        
//        double res = lr.predictOneInstance(predictingFile, clsIdx, list);
//        System.out.println("Traffic Volume: " + (res >= 0.0? (int)res: 0));
    }
    
}
