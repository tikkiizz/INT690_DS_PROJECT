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
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

/**
 *
 * @author user
 */
public class Utils {
    
    public static Instances getDataSet(String filename, int classIndex){
        try {
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File(filename));
            Instances dataSet = loader.getDataSet();
            dataSet.setClassIndex(classIndex);
            return dataSet;
        } catch (IOException ex) {
            Logger.getLogger(LinearRegressionML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void printAttribute(Instance ins){
        if(ins.numAttributes() == 0){
            return;
        }
        for (int i = 0; i < ins.numAttributes(); i++) {
            Attribute attr = ins.attribute(i);
            if(i==0){
                System.out.print(attr.name() + ": " + ins.toString(i));
            }
            else{
                System.out.print(", " + attr.name() + ": " + ins.toString(i));
            }
        }
        System.out.println("");
    }
}
