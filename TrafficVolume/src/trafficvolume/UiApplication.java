/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficvolume;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class UiApplication extends javax.swing.JFrame {
    private LinearRegressionML lr;
    private NeuralNetworkML nn;
    private int clsIdx;
    /**
     * Creates new form UiApplication
     */
    public UiApplication() {
        initComponents();
    }
    public UiApplication(LinearRegressionML lr, int clsIdx) {
        this.lr = lr;
        this.clsIdx = clsIdx;
        initComponents();
    }

    public UiApplication(NeuralNetworkML nn, int clsIdx) {
        this.nn = nn;
        this.clsIdx = clsIdx;
        initComponents();
    }
    
    
    private double predictInstance(){
        
        String holiday = (String) this.holidayCB.getSelectedItem();
        HashMap<String, Object> holidayMap = new HashMap<>();
        double hol = 0;
        if (!holiday.equals("None")){
            hol = 1; 
        }
        holidayMap.put("value", hol);
        holidayMap.put("type", "double");
        
        double temp = Double.parseDouble(this.tempTF.getText());
        HashMap<String, Object> tempMap = new HashMap<>();
        tempMap.put("value", temp + 273.15);
        tempMap.put("type", "double");
        
        double rain1Hr = Double.parseDouble(this.rainTF.getText());
        HashMap<String, Object> rainMap = new HashMap<>();
        rainMap.put("value", rain1Hr);
        rainMap.put("type", "double");
        
        double snow1Hr = Double.parseDouble(this.snowTF.getText());
        HashMap<String, Object> snowMap = new HashMap<>();
        snowMap.put("value", snow1Hr);
        snowMap.put("type", "double");
        
        double cloudsAll = Double.parseDouble(this.cloudTF.getText());
        HashMap<String, Object> cloudMap = new HashMap<>();
        cloudMap.put("value", cloudsAll);
        cloudMap.put("type", "double");
        
        String weatherDescription = (String) this.weatherDescCB.getSelectedItem();
        HashMap<String, Object> weatherDescMap = new HashMap<>();
        weatherDescMap.put("value", weatherDescription);
        weatherDescMap.put("type", "String");
        
        String day = (String) this.dayCB.getSelectedItem();
        String time = (String) this.timeCB.getSelectedItem();
        time = time.replace(":00", "");
        int newT = Integer.parseInt(time);
        String rangeT = "";
        if(newT >0 && newT <= 6){
            rangeT = "00-06";
        }else if (newT >6 && newT <= 10){
            rangeT = "06-10";
        }else if (newT >10 && newT <= 12){
            rangeT = "10-12";
        }else if (newT >12 && newT <= 14){
            rangeT = "12-14";
        }else if (newT >14 && newT <= 17){
            rangeT = "14-17";
        }else if (newT >17 && newT <= 20){
            rangeT = "17-20";
        }else {
            rangeT = "20-00";
        }
        
        String dayTime = day + " " + rangeT;
        HashMap<String, Object> dayTimeMap = new HashMap<>();
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
        double ans = 0;
        if(this.lr != null){
            ans = this.lr.predictOneInstance("predict.arff", this.clsIdx, list);
        }
        else{
            ans = this.nn.predictOneInstance("predict.arff", this.clsIdx, list);
        }
        return ans;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tempTF = new javax.swing.JTextField();
        rainTF = new javax.swing.JTextField();
        snowTF = new javax.swing.JTextField();
        cloudTF = new javax.swing.JTextField();
        holidayCB = new javax.swing.JComboBox<>();
        weatherDescCB = new javax.swing.JComboBox<>();
        dayCB = new javax.swing.JComboBox<>();
        predictBtn = new javax.swing.JButton();
        predictTxtArea = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        timeCB = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Temperature (°C): ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(490, 230, 140, 15);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Rain Amount (mm): ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(480, 280, 150, 15);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Snow Amount (mm): ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(470, 330, 150, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Holiday: ");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(120, 230, 60, 15);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Clouds Cover (%): ");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(490, 380, 116, 15);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Weather Description: ");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 380, 150, 15);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Day: ");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(140, 280, 31, 15);

        tempTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tempTF.setText("27");
        tempTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempTFActionPerformed(evt);
            }
        });
        getContentPane().add(tempTF);
        tempTF.setBounds(610, 230, 50, 21);

        rainTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rainTF.setText("0");
        getContentPane().add(rainTF);
        rainTF.setBounds(610, 280, 50, 21);

        snowTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        snowTF.setText("0");
        snowTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snowTFActionPerformed(evt);
            }
        });
        getContentPane().add(snowTF);
        snowTF.setBounds(610, 330, 50, 21);

        cloudTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cloudTF.setText("1");
        getContentPane().add(cloudTF);
        cloudTF.setBounds(610, 380, 50, 21);

        holidayCB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        holidayCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Christmas Day", "Columbus Day", "Independence Day", "Labor Day", "Memorial Day", "Thanksgiving Day", "Veterans Day", "Washingtons Birthday" }));
        getContentPane().add(holidayCB);
        holidayCB.setBounds(180, 230, 93, 21);

        weatherDescCB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        weatherDescCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SQUALLS", "broken clouds", "drizzle", "few clouds", "fog", "haze", "heavy intensity drizzle", "heavy intensity rain", "heavy snow", "light intensity drizzle", "light intensity shower rain", "light rain", "light shower snow", "light snow", "mist", "moderate rain", "overcast clouds", "proximity shower rain", "proximity thunderstorm", "proximity thunderstorm with drizzle", "proximity thunderstorm with rain", "scattered clouds", "shower snow", "sky is clear", "smoke", "snow", "thunderstorm", "thunderstorm with heavy rain", "thunderstorm with light drizzle", "thunderstorm with light rain", "thunderstorm with rain", "very heavy rain" }));
        weatherDescCB.setSelectedIndex(23);
        weatherDescCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weatherDescCBActionPerformed(evt);
            }
        });
        getContentPane().add(weatherDescCB);
        weatherDescCB.setBounds(180, 380, 280, 20);

        dayCB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dayCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));
        dayCB.setSelectedIndex(6);
        dayCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayCBActionPerformed(evt);
            }
        });
        getContentPane().add(dayCB);
        dayCB.setBounds(180, 280, 119, 21);

        predictBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        predictBtn.setText("Predict");
        predictBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                predictBtnMouseClicked(evt);
            }
        });
        getContentPane().add(predictBtn);
        predictBtn.setBounds(300, 420, 90, 25);

        predictTxtArea.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        predictTxtArea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(predictTxtArea);
        predictTxtArea.setBounds(220, 460, 252, 49);

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel8.setText("Traffic Volume Prediction");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(240, 190, 250, 43);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Time:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(130, 330, 40, 15);

        timeCB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        timeCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0:00", "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" }));
        timeCB.setSelectedIndex(9);
        timeCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeCBActionPerformed(evt);
            }
        });
        getContentPane().add(timeCB);
        timeCB.setBounds(180, 330, 60, 21);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trafficvolume/background.jpg"))); // NOI18N
        jLabel11.setText("jLabel11");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(0, 0, 700, 530);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tempTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tempTFActionPerformed

    private void predictBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_predictBtnMouseClicked
        // TODO add your handling code here:
        double ans = this.predictInstance();
        System.out.println("ans = " + ans);
        if(ans < 0.0){
            this.predictTxtArea.setText("0 units/area");
        }
        else{
            this.predictTxtArea.setText(Double.toString(ans)+" units/area");
        }
    }//GEN-LAST:event_predictBtnMouseClicked

    private void timeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeCBActionPerformed

    private void dayCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayCBActionPerformed

    private void weatherDescCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weatherDescCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weatherDescCBActionPerformed

    private void snowTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snowTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_snowTFActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UiApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UiApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UiApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UiApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UiApplication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cloudTF;
    private javax.swing.JComboBox<String> dayCB;
    private javax.swing.JComboBox<String> holidayCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JButton predictBtn;
    private javax.swing.JTextField predictTxtArea;
    private javax.swing.JTextField rainTF;
    private javax.swing.JTextField snowTF;
    private javax.swing.JTextField tempTF;
    private javax.swing.JComboBox<String> timeCB;
    private javax.swing.JComboBox<String> weatherDescCB;
    // End of variables declaration//GEN-END:variables

    
    public NeuralNetworkML getNN() {
        return nn;
    }

    public void setNN(NeuralNetworkML nn) {
        this.nn = nn;
    }
    
    public LinearRegressionML getLr() {
        return lr;
    }

    public void setLr(LinearRegressionML lr) {
        this.lr = lr;
    }

    public int getClsIdx() {
        return clsIdx;
    }

    public void setClsIdx(int clsIdx) {
        this.clsIdx = clsIdx;
    }
}
