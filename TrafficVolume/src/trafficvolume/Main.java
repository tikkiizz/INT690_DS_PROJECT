/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficvolume;

/**
 *
 * @author user
 */
public class Main {
    
    public static void main(String[] args) {
        String trainingFile = "train_80p100_38556.arff";
        String testingFile = "test_20p100_9638.arff";
        String predictingFile = "";
        int clsIdx = 7;
        String modelFileName = "traffic_volume.model";
        
        LinearRegressionML lr = new LinearRegressionML();
//        lr.setPredictingFileName(predictingFile);
        lr.trainAndTest(trainingFile, testingFile, clsIdx);
        lr.saveModel(modelFileName);
//        UiApplication app = new UiApplication();
//        app.setVisible(true);
    }
    
}
