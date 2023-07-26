import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameWindow extends JFrame implements ActionListener {
    private JLabel title, time, letter, score;
    private JButton card1, card2, card3, card4;
    private int points = 0;
    private int timeLeft = 15;
    private Timer timer;
    private Random random = new Random();
    private char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private char letterToFind;
    private boolean isCorrect = false;

    public GameWindow() {
        super("SignUs");
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 10, 10, 10);

        title = new JLabel("SignUs");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        add(title, constraints);

        time = new JLabel("Time: " + timeLeft);
        time.setFont(new Font("Arial", Font.BOLD, 20));
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        add(time, constraints);

        letter = new JLabel("A");
        letter.setFont(new Font("Arial", Font.BOLD, 50));
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        add(letter, constraints);

        card1 = new JButton();
        card1.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(card1, constraints);

        card2 = new JButton();
        card2.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(card2, constraints);

        card3 = new JButton();
        card3.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(card3, constraints);

        card4 = new JButton();
        card4.addActionListener(this);
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(card4, constraints);

        score = new JLabel("Score: " + points);
        score.setFont(new Font("Arial", Font.BOLD, 20));
        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(score, constraints);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                time.setText("Time: " + timeLeft);
                if (timeLeft == 0) {
                    timer.stop();
                    if (JOptionPane.showConfirmDialog(null, "Time is over. Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        setVisible(false);
                        new GameWindow();
                    } else {
                        System.exit(0);
                    }
                }
            }
        });
        timer.start();

        assignCards();

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class CardOptions {
      public char card1Value = ' ';
      public char card2Value = ' ';
      public char card3Value = ' ';
      public char card4Value = ' ';

    CardOptions() {
        this.card1Value = ' ';
        this.card2Value = ' ';
        this.card3Value = ' ';
        this.card4Value = ' ';
    }

      CardOptions(char card1Param, char card2Param, char card3Param, char card4Param) {
        this.card1Value = card1Param;
        this.card2Value = card2Param;
        this.card3Value = card3Param;
        this.card4Value = card4Param;
      }

      CardOptions(char[] vals) {
        this.card1Value = vals[0];
        this.card2Value = vals[1];
        this.card3Value = vals[2];
        this.card4Value = vals[3];
      }

      public boolean valuesAreUnique() {
        char[] cardArray = {card1Value, card2Value, card3Value, card4Value};

        boolean containsRepeated = false;

        for (int i = 0; i < cardArray.length; i++) {
            for (int j = 0; j < cardArray.length; j++) {
                if (cardArray[i] == cardArray[j] && i != j) {
                    containsRepeated = true;
                }
            }
        }

        return !containsRepeated;
      }

      public char generateRandomCharacter() {
        Random random = new Random();

        return letters[random.nextInt(26)];
      }

      public CardOptions generateOptions(int respectedIndex, char respectedValue) {
        while (true) {

            char[] randomlyCreatedCharacters = new char[4];

            for (int i = 0; i < randomlyCreatedCharacters.length; i++ ) {
                randomlyCreatedCharacters[i] = generateRandomCharacter();
            }

            randomlyCreatedCharacters[respectedIndex] = respectedValue;

            CardOptions newCardOptions = new CardOptions(randomlyCreatedCharacters);

            if (newCardOptions.valuesAreUnique()) {
                return newCardOptions;
            }
        }
      }


    }

    private void assignCards() {
        card1.setBackground(Color.GRAY);
        card2.setBackground(Color.GRAY);
        card3.setBackground(Color.GRAY);
        card4.setBackground(Color.GRAY);



        int randomCard = random.nextInt(3);
        int randomLetter = random.nextInt(26);
        letterToFind = letters[randomLetter];
        CardOptions myBeatifulCards = new CardOptions().generateOptions(randomCard, letterToFind);
        letter.setText(String.valueOf(letterToFind));

        card1.setText(String.valueOf(myBeatifulCards.card1Value));
        card2.setText(String.valueOf(myBeatifulCards.card2Value));
        card3.setText(String.valueOf(myBeatifulCards.card3Value));
        card4.setText(String.valueOf(myBeatifulCards.card4Value));
        // switch (randomCard) {
        //     case 0:
        //         card1.setText(String.valueOf(letterToFind));
        //         card2.setText(String.valueOf(letters[random.nextInt(26)]));
        //         card3.setText(String.valueOf(letters[random.nextInt(26)]));
        //         card4.setText(String.valueOf(letters[random.nextInt(26)]));

        //         break;
        //     case 1:
        //         card1.setText(String.valueOf(letters[random.nextInt(26)]));
        //         card2.setText(String.valueOf(letterToFind));
        //         card3.setText(String.valueOf(letters[random.nextInt(26)]));
        //         card4.setText(String.valueOf(letters[random.nextInt(26)]));

        //         break;
        //     case 2:
        //         card1.setText(String.valueOf(letters[random.nextInt(26)]));
        //         card2.setText(String.valueOf(letters[random.nextInt(26)]));
        //         card3.setText(String.valueOf(letterToFind));
        //         card4.setText(String.valueOf(letters[random.nextInt(26)]));

        //         break;
        //     case 3:
        //         card1.setText(String.valueOf(letters[random.nextInt(26)]));
        //         card2.setText(String.valueOf(letters[random.nextInt(26)]));
        //         card3.setText(String.valueOf(letters[random.nextInt(26)]));
        //         card4.setText(String.valueOf(letterToFind));

        //         break;
        // }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == card1) {
            if (card1.getText().equals(String.valueOf(letterToFind))) {
                isCorrect = true;
            } else {
              card1.setBackground(Color.RED);
	            card1.setText("Incorrect:(");
            }
        } else if (e.getSource() == card2) {
            if (card2.getText().equals(String.valueOf(letterToFind))) {
                isCorrect = true;
            } else {
              card2.setBackground(Color.RED);
	            card2.setText("Incorrect:(");
            }
        } else if (e.getSource() == card3) {
            if (card3.getText().equals(String.valueOf(letterToFind))) {
                isCorrect = true;
            } else {
              card3.setBackground(Color.RED);
	            card3.setText("Incorrect:(");
            }
        } else if (e.getSource() == card4) {
            if (card4.getText().equals(String.valueOf(letterToFind))) {
                isCorrect = true;
            } else {
              card4.setBackground(Color.RED);
	            card4.setText("Incorrect:(");
            }
        }
        if (isCorrect) {
            points++;
            score.setText("Score: " + points);
            assignCards();
        }
        isCorrect = false;
    }
}
