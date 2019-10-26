/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficvolume;

import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

/**
 *
 * @author user
 */
public class LinearRegressionML {
    
    private Classifier classifier;
    
    public LinearRegressionML() {
    }
    
    public void trainAndTest(String trainingFileName, String testingFileName, int classIndex){
        try {
            Instances trainingDataSet =  Utils.getDataSet(trainingFileName, classIndex);
            Instances testingDataSet = Utils.getDataSet(testingFileName, classIndex);
            this.classifier = new LinearRegression();
            this.classifier.buildClassifier(trainingDataSet);
            Evaluation eval = new Evaluation(trainingDataSet);
            eval.evaluateModel(this.classifier, testingDataSet);
            System.out.println(this.classifier);
            System.out.println(eval.toSummaryString());
        } catch (Exception ex) {
            Logger.getLogger(LinearRegressionML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveModel(String filename){
        try {
            SerializationHelper.write(filename, this.classifier);
        } catch (Exception ex) {
            Logger.getLogger(LinearRegressionML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadModel(String filename){
        try {
            this.classifier = (LinearRegression) SerializationHelper.read(filename);
        } catch (Exception ex) {
            Logger.getLogger(LinearRegressionML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void predictDataSet(String predictingFileName, int classIndex){
        
        Instance predictionDataSet;
        double answerValue = 0;        
        Instances predictDataSets = Utils.getDataSet(predictingFileName, classIndex);
        for (int i = 0; i < predictDataSets.numInstances(); i++){ 
            try {
                predictionDataSet = predictDataSets.instance(i);
                answerValue = this.classifier.classifyInstance(predictionDataSet);
            } catch (Exception ex) {
                Logger.getLogger(LinearRegressionML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String predictOneInstance(String predictingFileName, int classIndex, String attr){
        
        try {
            String ans = "";
            Instance predictDataSet = Utils.getDataSet(predictingFileName, classIndex).instance(0);
            predictDataSet.setValue(0, attr);
            
            double value = this.classifier.classifyInstance(predictDataSet);
            return ans;
            
        } catch (Exception ex) {
            Logger.getLogger(LinearRegressionML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }
    
}
