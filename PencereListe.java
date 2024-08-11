
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PencereListe extends JFrame {
    private JPanel jpanel1;
    private JTable table1;
    private JScrollPane tableScroll;
    private JButton ekleButton;
    private JButton silButton;

    Connection con;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public PencereListe(){
        add(jpanel1);
        setSize(800,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        con=Veritabani.vbBaglan();
        getirGorevler();
        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                PencereEkle pencereEkle=new PencereEkle();
                pencereEkle.setVisible(true);


            }
        });
        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sutun=0;
                int satir=table1.getSelectedColumn();
                String grv_id=table1.getModel().getValueAt(satir,sutun).toString();


                try {
                    preparedStatement=con.prepareStatement("DELETE FROM gorevler WHERE ID=?");
                    preparedStatement.setString(1,grv_id);
                    preparedStatement.executeUpdate();
                    getirGorevler();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }



            }
        });

        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void getirGorevler(){
        try {
            preparedStatement= con.prepareStatement("SELECT ID,gorevAdi,onem,zorluk FROM gorevler");
            resultSet=preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
