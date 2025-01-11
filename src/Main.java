import javax.swing.*;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.net.URL;
import java.net.URLConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
    }
}

class MyFrame extends JFrame {
    public MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("HTTPConnection");
        setSize(800, 600);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 0;
        constraints.weighty = 0;

        add(new JLabel("URL:"), constraints, 0, 0, 1, 1);
        add(new JLabel("Method:"), constraints, 0, 1, 1, 1);
        add(new JLabel("Post Data:"), constraints, 0, 2, 1, 1);
        constraints.weightx = 100;
        constraints.weighty = 100;
        JTextField url = new JTextField();
        JTextField postData = new JTextField();
        postData.setEnabled(false);
        JTextArea response = new JTextArea();
        response.setEditable(false);
        JButton action = new JButton("GET");
        action.addActionListener(evt -> {
            try {
                URL aUrl = new URL(url.getText());
                if (action.getText().equals("GET")) {
                    response.setText("");
                    BufferedReader in = new BufferedReader(new InputStreamReader(aUrl.openStream()));
                    String line;
                    while((line = in.readLine()) != null)
                        response.append(line);
                    in.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        JComboBox<String> method = new JComboBox<>(new String[] {"GET", "POST"});
        method.addActionListener(e -> {
            action.setText((String) method.getSelectedItem());
            postData.setEnabled(action.getText().equals("POST"));
        });
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        add(url, constraints, 1, 0, 1, 1);
        add(method, constraints, 1, 1, 1, 1);
        add(postData, constraints, 1, 2, 1, 1);
        add(action, constraints, 0, 3, 1, 1);
        constraints.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(response), constraints, 0, 4, 2, 1);
    }
    public void add(Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        add(c, constraints);
    }
}