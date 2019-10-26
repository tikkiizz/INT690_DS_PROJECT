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
        String trainingFile = "";
        String testingFile = "";
        String predictingFile = "";
        int clsIdx = 1;
        LinearRegressionML lr = new LinearRegressionML();
//        lr.setClassIndex(clsIdx);
//        lr.setTrainingFileName(trainingFile);
//        lr.setTestingFileName(testingFile);
//        lr.setPredictingFileName(predictingFile);
//        lr.process();
        UiApplication app = new UiApplication();
        app.setVisible(true);
    }
    
}
