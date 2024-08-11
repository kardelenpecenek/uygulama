import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PencereEkle  extends  JFrame{
    private JPanel jpanel1;
    private JTextArea TgorevAdi;
    private JTextArea Tonem;
    private JTextArea Tzorluk;

    private JButton vazgecButton;
    private JButton kaydetButton;

    Connection con;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public PencereEkle(){
        add(jpanel1);
        setSize(800,400);
        setTitle("Öğrenci Ekle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        con=Veritabani.vbBaglan();

        vazgecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                PencereListe pencereListe=new PencereListe();
                pencereListe.setVisible(true);


            }
        });


        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int zorluk1;
                String gorevAdi1;
                String onem1;



                gorevAdi1= TgorevAdi.getText();
                onem1=Tonem.getText();
                zorluk1= Integer.parseInt(Tzorluk.getText());

                try{
                    preparedStatement=con.prepareStatement("INSERT INTO gorevler (gorevAdi,onem,zorluk) VALUES (?,?,?)");
                    preparedStatement.setString(1, gorevAdi1);
                    preparedStatement.setString(2,onem1);
                    preparedStatement.setInt(3, zorluk1);

                    preparedStatement.executeUpdate();
                    setVisible(false);
                    dispose();
                    PencereListe pencereListe=new PencereListe();
                    pencereListe.setVisible(true);

                }catch (SQLException ex){
                    throw new RuntimeException();
                }

            }
        });



    }
}
