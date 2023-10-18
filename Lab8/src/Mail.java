import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Mail {

    static JFrame frame = new JFrame("Button races");
    static JPanel MainPanel = new JPanel();

    static MimeMessage message;

    public static void createAndShowGUI() throws MessagingException, IOException {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        MainPanel.setLayout(new CardLayout());

        JPanel AuthorisationPanel = new JPanel();
        JPanel MailCreatePanel = new JPanel();
        JPanel MailSendSuccess = new JPanel();
        JPanel MailSendFail = new JPanel();

        MainPanel.add(AuthorisationPanel);
        MainPanel.add(MailCreatePanel);
        MainPanel.add(MailSendSuccess);
        MainPanel.add(MailSendFail);

        String[] AuthorizationData;
        AuthorizationData = AuthorisationPanel(AuthorisationPanel);

        MailPanel(MailCreatePanel,AuthorizationData);

        MailSendSuccessPanel(MailSendSuccess);
        MailSendFailPanel(MailSendFail);

        frame.setContentPane(MainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static String[] AuthorisationPanel(Container pane){

        String[] LogPasswd = new String[2];

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel MainAuthorisationLabel = new JLabel("Authorization");
        JLabel MailLabel = new JLabel("Mail login");
        JLabel PasswdLabel = new JLabel("Password");

        JTextField LoginField = new JTextField();
        JTextField PasswordField = new JTextField();

        JButton Next = new JButton("Login");

        {
            c.fill = GridBagConstraints.HORIZONTAL;

            c.insets = new Insets(5,5,5,5);

            c.gridx = 0;
            c.gridwidth = 5;
            c.gridy = 0;

            MainAuthorisationLabel.setFont(new Font("Arial",Font.PLAIN,20));
            pane.add(MainAuthorisationLabel,c);

            c.gridy = 1;
            c.gridwidth = 1;

            pane.add(MailLabel,c);

            c.gridx = 1;
            c.gridwidth = 2;

            pane.add(LoginField,c);

            c.gridx = 0;
            c.gridwidth = 1;
            c.gridy = 2;

            pane.add(PasswdLabel,c);

            c.gridx = 1;
            c.gridwidth = 2;
            c.ipadx = 200;

            pane.add(PasswordField,c);

            c.gridy = 3;
            c.gridwidth = 1;

            pane.add(Next,c);
        }

        Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogPasswd[0] = LoginField.getText();
                LogPasswd[1] = PasswordField.getText();
                CardLayout cardLayout = (CardLayout) MainPanel.getLayout();
                cardLayout.next(MainPanel);
            }
        });
        return LogPasswd;
    }

    private synchronized static void MailPanel(Container pane, String[] AuthData) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        final String[] Mail = new String[3];

        JLabel ToLabel = new JLabel("To");
        JLabel Subjectlabel = new JLabel("Subject");
        JLabel TextLabel = new JLabel("Text");

        JTextField ToField = new JTextField();
        JTextField SubjectField = new JTextField();
        JTextArea TextArea = new JTextArea(5, 10);

        JButton MailSend = new JButton("Send Mail");

        {
            c.fill = GridBagConstraints.HORIZONTAL;

            c.insets = new Insets(5, 5, 5, 5);

            c.gridx = 0;
            c.gridy = 0;

            pane.add(ToLabel, c);

            c.gridy = 1;

            pane.add(Subjectlabel, c);

            c.gridy = 2;

            pane.add(TextLabel, c);

            c.gridx = 1;
            c.gridy = 0;
            c.ipadx = 200;

            pane.add(ToField, c);

            c.gridy = 1;

            pane.add(SubjectField, c);

            c.gridy = 2;

            TextArea.setLineWrap(true);
            TextArea.setWrapStyleWord(true);

            pane.add(new JScrollPane(TextArea), c);

            c.gridy = 3;
            c.gridx = 1;

            pane.add(MailSend,c);
        }

        MailSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mail[0] = ToField.getText();
                Mail[1] = SubjectField.getText();
                Mail[2] = TextArea.getText();
                MailSend.setVisible(false);

                new MailSender(Mail, AuthData).start();
                CardLayout cardLayout = (CardLayout) MainPanel.getLayout();
                cardLayout.next(MainPanel);
            }
        });


    }

    private static void MailSendSuccessPanel(Container pane){
        JLabel label = new JLabel("Mail Send Successfully");
        label.setFont(new Font("Arial",Font.PLAIN,40));
        pane.add(label);
    }

    private static void MailSendFailPanel(Container pane){
        JLabel label = new JLabel("Mail Send fail, try again");
        label.setFont(new Font("Arial",Font.PLAIN,40));
        pane.add(label);
    }

    static class MailSender extends Thread{
        String[] Mail;
        String[] AuthData;
        public MailSender(String[] Mail,String[] AuthData){
            this.Mail =Mail;
            this.AuthData = AuthData;
        }

        @Override
        public void run() {
            try {
                Authenticator auth = new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(AuthData[0], AuthData[1]);
                    }
                };

                Properties properties = new Properties();
                properties.load(new FileReader(new File("Mail.properties")));

                Session session = Session.getInstance(properties, auth);

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(AuthData[0]));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(Mail[0]));
                message.setSubject(Mail[1]);
                message.setText(Mail[2]);
                Transport.send(message);
                System.out.println("Message sent");

            }catch (IOException| MessagingException e){
                e.printStackTrace();
            }
        }
    }
    public static void MailSend(String[] Mail,String[] AuthData) throws IOException, MessagingException {


        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(AuthData[0],AuthData[1]);
            }
        };

        Properties properties = new Properties();
        properties.load(new FileReader(new File("Mail.properties")));

        Session session = Session.getInstance(properties,auth);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(AuthData[0]));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(Mail[0]));
        message.setSubject(Mail[1]);
        message.setText(Mail[2]);
        Transport.send(message);
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (MessagingException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
