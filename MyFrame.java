import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    JFrame frame;
    JPanel entry;
    JLabel positionText;
    JTextField name;
    JTextField ppg;
    JTextField per;
    JTextField tsp;
    JTextField gp;
    JTextField twp;
    JTextField ts;
    JTextField cmvp;
    JButton submit;
    JButton vote;
    JButton delete; // New delete button
    String nameList;
    JLabel names;
    Player[] players;
    int count;
    Voter[] votingCommittee;

    MyFrame() {

        // initializing variables
        count = 0;

        nameList = "";

        players = new Player[20];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }

        votingCommittee = new Voter[100];
        for (int i = 0; i < votingCommittee.length; i++) {
            votingCommittee[i] = new Voter(players);
        }

        frame = new JFrame();

        entry = new JPanel();

        positionText = new JLabel();
        names = new JLabel(nameList);

        name = new JTextField("Name");
        name.setPreferredSize(new Dimension(175, 25));
        ppg = new JTextField("PPG");
        ppg.setPreferredSize(new Dimension(100, 25));
        per = new JTextField("PER");
        per.setPreferredSize(new Dimension(100, 25));
        tsp = new JTextField("TS%");
        tsp.setPreferredSize(new Dimension(100, 25));
        gp = new JTextField("Games Played");
        gp.setPreferredSize(new Dimension(100, 25));
        twp = new JTextField("Team Winning Percentage");
        twp.setPreferredSize(new Dimension(100, 25));
        ts = new JTextField("Team Seed");
        ts.setPreferredSize(new Dimension(100, 25));
        cmvp = new JTextField("Consecutive MVPs");
        cmvp.setPreferredSize(new Dimension(100, 25));

        submit = new JButton("Submit");
        vote = new JButton("Vote");
        delete = new JButton("Delete"); // Initialize delete button

        positionText.setText("Enter Player Info");

        submit.setPreferredSize(new Dimension(100, 25));
        submit.addActionListener(this);

        vote.setPreferredSize(new Dimension(100, 25));
        vote.addActionListener(this);

        delete.setPreferredSize(new Dimension(100, 25));
        delete.addActionListener(this);

        entry.setBounds(0, 0, 800, 150);
        entry.add(positionText);
        entry.add(name);
        entry.add(ppg);
        entry.add(per);
        entry.add(tsp);
        entry.add(gp);
        entry.add(twp);
        entry.add(ts);
        entry.add(cmvp);
        entry.add(submit);
        entry.add(vote);
        entry.add(delete); // Add delete button
        entry.add(names);

        this.setSize(700, 700);
        this.add(entry);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            players[count] = new Player(name.getText(), Double.parseDouble(ppg.getText()), Double.parseDouble(per.getText()), Double.parseDouble(tsp.getText()), Double.parseDouble(twp.getText()), Double.parseDouble(ts.getText()), Double.parseDouble(gp.getText()), Double.parseDouble(cmvp.getText()));
            nameList += players[count].getName() + ", ";
            names.setText(nameList);
            count++;
        }

        if (e.getSource() == vote) {
            // voters vote
            for (Voter v : votingCommittee) {
                v.vote();
            }

            // sort by voteScore
            Player temp;
            for (int i = players.length - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (players[j].getVoteScore() > players[j + 1].getVoteScore()) {
                        temp = players[j];
                        players[j] = players[j + 1];
                        players[j + 1] = temp;
                    }
                }
            }

            // send results
            String result = "1) " + players[players.length - 1].getName() + " " + players[players.length - 1].getVoteScore() + " (" + players[players.length - 1].getFirstVotes() + ")" + "\n" +
                    "2) " + players[players.length - 2].getName() + " " + players[players.length - 2].getVoteScore() + " (" + players[players.length - 2].getFirstVotes() + ")" + "\n" +
                    "3) " + players[players.length - 3].getName() + " " + players[players.length - 3].getVoteScore() + " (" + players[players.length - 3].getFirstVotes() + ")" + "\n" +
                    "4) " + players[players.length - 4].getName() + " " + players[players.length - 4].getVoteScore() + " (" + players[players.length - 4].getFirstVotes() + ")" + "\n" +
                    "5) " + players[players.length - 5].getName() + " " + players[players.length - 5].getVoteScore() + " (" + players[players.length - 5].getFirstVotes() + ")" + "\n" +
                    "6) " + players[players.length - 6].getName() + " " + players[players.length - 6].getVoteScore() + " (" + players[players.length - 6].getFirstVotes() + ")" + "\n";
            JOptionPane.showMessageDialog(null, result, "Results", JOptionPane.PLAIN_MESSAGE);

        }

        // delete last player
        if (e.getSource() == delete) {
            if (count > 0) {
                count--;
                nameList = nameList.substring(0, nameList.lastIndexOf(players[count].getName()));
                names.setText(nameList);
                players[count] = new Player();
            } else {
                JOptionPane.showMessageDialog(null, "No players to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}