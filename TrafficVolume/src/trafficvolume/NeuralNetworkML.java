/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficvolume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Debug.Random;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

/**
 *
 * @author user
 */
public class NeuralNetworkML {
    private MultilayerPerceptron classifier;
    private Instance instance;

    public NeuralNetworkML() {
    }

    public void trainAndTest(String trainingFileName, String testingFileName, int classIndex){
        try {
            Instances trainingDataSet =  Utils.getDataSet(trainingFileName, classIndex);
            Instances testingDataSet = Utils.getDataSet(testingFileName, classIndex);
            this.classifier = new MultilayerPerceptron();
            
            this.classifier.setLearningRate(0.0025);
            this.classifier.setMomentum(0.15);
            this.classifier.setTrainingTime(100);
            //this.classifier.setHiddenLayers("3");
            this.classifier.buildClassifier(trainingDataSet);
            Evaluation eval = new Evaluation(trainingDataSet);
            //eval.crossValidateModel(this.classifier, trainingDataSet, 10, new Random(1));
            eval.evaluateModel(this.classifier, testingDataSet);
            System.out.println(this.classifier);
            System.out.println(eval.toSummaryString());
//            System.out.println(eval.correct());
            System.out.println(eval.errorRate());
//            System.out.println(eval.correlationCoefficient());
//            System.out.println(eval.totalCost());
        } catch (Exception ex) {
            Logger.getLogger(NeuralNetworkML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveModel(String filename){
        try {
            SerializationHelper.write(filename, this.classifier);
        } catch (Exception ex) {
            Logger.getLogger(NeuralNetworkML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadModel(String filename){
        try {
            this.classifier = (MultilayerPerceptron) SerializationHelper.read(filename);
        } catch (Exception ex) {
            Logger.getLogger(NeuralNetworkML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void predictDataSet(String predictingFileName, int classIndex){
        
        Instance instance;
        int answerValue;        
        Instances predictingDataSet = Utils.getDataSet(predictingFileName, classIndex);
        for (int i = 0; i < predictingDataSet.numInstances(); i++){ 
            try {
                instance = predictingDataSet.instance(i);
                answerValue = (int)this.classifier.classifyInstance(instance);
                Utils.printAttribute(instance);
                System.out.println(instance.attribute(classIndex).name() + ": " + answerValue);
            } catch (Exception ex) {
                Logger.getLogger(NeuralNetworkML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public double predictOneInstance(String predictingFileName, int classIndex, ArrayList<HashMap<String, Object>> list){
        
        try {
            if(this.instance == null){
                this.instance = Utils.getDataSet(predictingFileName, classIndex).instance(0);
            }
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, Object> map = list.get(i);
                String type = (String)map.get("type");
                if(type.equals("String")){
                    this.instance.setValue(i, (String)map.get("value"));
                }
                else{
                    this.instance.setValue(i, (double)map.get("value"));
                }
            }
            double answerValue = (int)this.classifier.classifyInstance(this.instance);
            Utils.printAttribute(this.instance);
            
            return answerValue;
            
        } catch (Exception ex) {
            Logger.getLogger(NeuralNetworkML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }
    
    public MultilayerPerceptron getClassifier() {
        return classifier;
    }

    public void setClassifier(MultilayerPerceptron classifier) {
        this.classifier = classifier;
    }
    
}
