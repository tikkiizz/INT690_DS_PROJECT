/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficvolume;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

/**
 *
 * @author user
 */
public class LinearRegressionML {

    private String trainingFileName;
    private String testingFileName;
    private String predictFileName;
    private int classIndex;
    private Classifier classifier;
    
    public LinearRegressionML() {
    }

    public LinearRegressionML(String trainingFileName, String testingFileName, int classIndex) {
        this.trainingFileName = trainingFileName;
        this.testingFileName = testingFileName;
        this.classIndex = classIndex;
    }

    public LinearRegressionML(String trainingFileName, String testingFileName, String predictFileName, int classIndex) {
        this.trainingFileName = trainingFileName;
        this.testingFileName = testingFileName;
        this.predictFileName = predictFileName;
        this.classIndex = classIndex;
    }
    
    
    public Instances getDataSet(String filename){
        try {
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File(filename));
            Instances dataSet = loader.getDataSet();
            dataSet.setClassIndex(this.classIndex);
            return dataSet;
            
        } catch (IOException ex) {
            Logger.getLogger(LinearRegressionML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void process(){
        try {
            Instances trainingDataSet = getDataSet(this.trainingFileName);
            Instances testingDataSet = getDataSet(this.testingFileName);
            this.classifier = new LinearRegression();
            this.classifier.buildClassifier(trainingDataSet);
            Evaluation eval = new Evaluation(trainingDataSet);
            eval.evaluateModel(classifier, testingDataSet);
        } catch (Exception ex) {
            Logger.getLogger(LinearRegressionML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void predictDataSet(){
        
        Instance predictionDataSet;
        double answerValue = 0;        
        Instances predictDataSets = getDataSet(this.predictFileName);
        for (int i = 0; i < predictDataSets.numInstances(); i++){ 
            try {
                predictionDataSet = predictDataSets.instance(i);
                answerValue = this.classifier.classifyInstance(predictionDataSet);
            } catch (Exception ex) {
                Logger.getLogger(LinearRegressionML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String predictOneInstance(String attr){
        
        try {
            String ans = "";
            Instance predictDataSet = getDataSet(this.predictFileName).instance(0);
            predictDataSet.setValue(0, attr);
            
            double value = this.classifier.classifyInstance(predictDataSet);
            return ans;
            
        } catch (Exception ex) {
            Logger.getLogger(LinearRegressionML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }


    public String getPredictFileName() {
        return predictFileName;
    }

    public void setPredictFileName(String predictFileName) {
        this.predictFileName = predictFileName;
    }

    public String getTrainingFileName() {
        return trainingFileName;
    }

    public void setTrainingFileName(String trainingFileName) {
        this.trainingFileName = trainingFileName;
    }

    public String getTestingFileName() {
        return testingFileName;
    }

    public void setTestingFileName(String testingFileName) {
        this.testingFileName = testingFileName;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }
    
}
