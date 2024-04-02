import Project.ConnectionProvider;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class QuizExamQuestion extends javax.swing.JFrame {

    public String code;
    public String id = "01";
    public String answer;
    public int min = 0;
    public int sec = 0;
    public int marks = 0;
    public int duration;
    public int per_mk;
    public String total_question;
    public String teacher = " ";
    public String teach;
    public String teachset;
    public String StuId;
    public String p;
    public String up;
    public String name;
    
    
    
    
    public void answerCheck()
    {
        String studentAnswer = "";
        if(jRadioButton1.isSelected())
        {
            studentAnswer = jRadioButton1.getText();
        }
        else if(jRadioButton2.isSelected())
        {
            studentAnswer = jRadioButton2.getText();
        }
        else if(jRadioButton3.isSelected())
        {
            studentAnswer = jRadioButton3.getText();
        }
        else 
        {
            studentAnswer = jRadioButton4.getText();
        }
        
        if(studentAnswer.equals(answer))
        {
            //System.out.println("mk"+per_mk);
            marks += per_mk ;
            //System.out.println("mk"+marks);
            
        }
        
        int ID = Integer.parseInt(id);
        ID++;
        if(ID <= 9)
        {
            id = String.valueOf(ID);
            id = "0"+id;
        }
        else
        {
            id = String.valueOf(ID);
            
        }
        
        
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        
        
        if(id.equals(total_question))
        {
            jButton1.setVisible(false);
        }
        
        
    }
    public void question()
    {
        if(teacher.equals("tonoy"))
        {
           teach = "select *from tonoy where id = '"+id+"' "; 
        }
        else if(teacher.equals("israt"))
        {
           teach = "select *from israt where id = '"+id+"' "; 
        }
        else if(teacher.equals("mostafa"))
        {
           teach = "select *from mostafa where id = '"+id+"' "; 
        }
        else if(teacher.equals("hridoy"))
        {
           teach = "select *from ems where id = '"+id+"' "; 
        }
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            
            
            ResultSet rsl = st.executeQuery(teach);
            
            while(rsl.next())
            {
                jLabel16.setText(rsl.getString(1));
                jLabel19.setText(rsl.getString(2));
                jRadioButton1.setText(rsl.getString(3));
                jRadioButton2.setText(rsl.getString(4));
                jRadioButton3.setText(rsl.getString(5));
                jRadioButton4.setText(rsl.getString(6));
                answer = rsl.getString(7);
            }
        }
        catch(Exception  e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void submit()
    {
        String StuId = jLabel10.getText();
        //answerCheck();
        
        String marks1 = String.valueOf(marks);
        
        if(teacher.equals("tonoy"))
        {
           up = "update tonoy_result set marks = '"+marks1+"' where StuId = '"+StuId+"' ";
        }
        else if(teacher.equals("israt"))
        {
           up = "update israt_result set marks = '"+marks1+"' where StuId = '"+StuId+"' "; 
        }
        else if(teacher.equals("mostafa"))
        {
           up = "update mostafa_result set marks = '"+marks1+"' where StuId = '"+StuId+"' ";
        }
        else if(teacher.equals("hridoy"))
        {
           up = "update studentdetails set marks = '"+marks1+"' where StuId = '"+StuId+"' ";
        }
        
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            
            st.executeUpdate(up);
            
            setVisible(false);
            new MarksShow(marks1, code, name,StuId).setVisible(true);
            
            
            
        }
        catch(Exception  e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public QuizExamQuestion() {
        initComponents();
    }
    
    Timer time;
    public QuizExamQuestion(String StuId , String tc) {
        initComponents();
        
        this.StuId = StuId;
        teacher = tc;
        //code = StuCourse;
        
        
        
        String id = "01";
        
        
        jLabel10.setText(StuId);
        
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        jLabel3.setText(dFormat.format(date));
        
        //System.out.println(teacher);
        
        
        if(teacher.equals("tonoy"))
        {
           p = "select *from tonoy_result where StuId='" + StuId + "'"; 
        }
        else if(teacher.equals("israt"))
        {
           p = "select *from israt_result where StuId='" + StuId + "'"; 
        }
        else if(teacher.equals("mostafa"))
        {
           p = "select *from mostafa_result where StuId='" + StuId + "'"; 
        }
        else if(teacher.equals("hridoy"))
        {
           p = "select *from studentdetails where StuId='" + StuId + "'"; 
        }
        
        if(teacher.equals("tonoy"))
        {
           teachset = "select *from tonoy where id = '"+id+"' "; 
        }
        else if(teacher.equals("israt"))
        {
           teachset = "select *from israt where id = '"+id+"' "; 
        }
        else if(teacher.equals("mostafa"))
        {
           teachset = "select *from mostafa where id = '"+id+"' "; 
        }
        else if(teacher.equals("hridoy"))
        {
           teachset = "select *from ems where id = '"+id+"' "; 
        }
        
        
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            //ResultSet rs = st.executeQuery("select *from studentdetails where StuId = '"+StuId+"' ");
            ResultSet rs = st.executeQuery(p);
            while(rs.next())
            {
                name = rs.getString(1);
                jLabel12.setText(name);
                code = rs.getString(5);
                
            }
            
            ResultSet rsl = st.executeQuery(teachset);
            
            while(rsl.next())
            {
                jLabel16.setText(rsl.getString(1));
                jLabel19.setText(rsl.getString(2));
                jRadioButton1.setText(rsl.getString(3));
                jRadioButton2.setText(rsl.getString(4));
                jRadioButton3.setText(rsl.getString(5));
                jRadioButton4.setText(rsl.getString(6));
                answer = rsl.getString(7);
            }
        }
        catch(Exception  e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
        String k = "select *from questiondetails where coursecode ='" + code + "'";
        
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            
            
            ResultSet rsl = st.executeQuery(k);
            
            while(rsl.next())
            {
                total_question = rsl.getString(3);
                
                int ls = Integer.parseInt(total_question);
                if(ls <= 9)
                {
                    total_question = String.valueOf(ls);
                    total_question = "0"+total_question;
                }
                else
                {
                    total_question = String.valueOf(ls);

                }
                jLabel14.setText(total_question);
                
                
                String tm = rsl.getString(4);
                duration = Integer.parseInt(tm);
               
                jLabel17.setText(tm);
                
                String mk = rsl.getString(5);
                per_mk = Integer.parseInt(mk);
                
                
                
                //teacher = rsl.getString(6);
                //System.out.println(teacher);
                
            }
        }
        catch(Exception  e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
      
        
        setLocationRelativeTo(this);
        time = new Timer(1000,new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                jLabel8.setText(String.valueOf(sec));
                jLabel7.setText(String.valueOf(min));
                
                if(sec == 60)
                {
                    sec = 0;
                    min++;
                    
                    if(min == duration)
                    {
                        time.stop();
                        answerCheck();
                        submit();
                    }
                }
                sec++;
            }
        }
        );
        time.start();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setLocation(new java.awt.Point(20, 30));
        setMaximumSize(new java.awt.Dimension(1200, 600));
        setMinimumSize(new java.awt.Dimension(1200, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));

        jLabel1.setFont(new java.awt.Font("Algerian", 1, 40)); // NOI18N
        jLabel1.setText("Question Paper");

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel2.setText("Date : ");

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel3.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel4.setText("Total Time : ");

        jLabel5.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel5.setText("minutes");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("jLabel17");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(290, 290, 290)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 1260, 80));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel9.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        jLabel9.setText("Student ID        : ");

        jLabel10.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel10.setText("CSE2102023012");

        jLabel11.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        jLabel11.setText("Student Name :");

        jLabel12.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel12.setText("Salman Johir Tonoy");

        jLabel13.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        jLabel13.setText("Total Question :");

        jLabel14.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel14.setText("10");

        jLabel15.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        jLabel15.setText("Question No     :");

        jLabel16.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel16.setText("00");

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        jLabel6.setText("Time Taken      : ");

        jLabel7.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel7.setText("00");

        jLabel8.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel8.setText("00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel16))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13)
                            .addComponent(jLabel6))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel10)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 360, 520));

        jLabel19.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        jLabel19.setText("Question Demo ?");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 127, -1, -1));

        jRadioButton1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 195, -1, -1));

        jRadioButton2.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jRadioButton2.setText("jRadioButton2");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 275, -1, -1));

        jRadioButton3.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jRadioButton3.setText("jRadioButton3");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 355, -1, -1));

        jRadioButton4.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jRadioButton4.setText("jRadioButton4");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 430, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 204, 204));
        jButton1.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Next.png"))); // NOI18N
        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 508, -1, 40));

        jButton2.setBackground(new java.awt.Color(0, 204, 204));
        jButton2.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 508, -1, 40));

        jPanel3.setBackground(new java.awt.Color(0, 153, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 890, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        answerCheck();
        question();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        int res = JOptionPane.showConfirmDialog(this , "Do you want to submit?" , "Confirmation" ,  JOptionPane.YES_NO_OPTION);
        if(res == JOptionPane.YES_OPTION)
        {
            answerCheck();
            submit();    
        }
                
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton1.isSelected())
        {
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton2.isSelected())
        {
            jRadioButton1.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton3.isSelected())
        {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton4.isSelected())
        {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(QuizExamQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuizExamQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuizExamQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuizExamQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuizExamQuestion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    // End of variables declaration//GEN-END:variables
}
