package ui.screens;

import ui.Screen;
import ui.ScreenFactory;
import ui.ScreenManager;
import ui.ScreenType;
import utils.Constants;
import utils.ThemeManager;

import javax.swing.*;
import java.awt.*;

public class HelpScreen extends Screen {
    public HelpScreen() {
        this.setLayout(new GridBagLayout());

        var mainColumn = Box.createVerticalBox();
        this.add(mainColumn);

        // TODO: Fix help text
        var helpText = """
                                
                AIM
                                
                • You are in KOC University Campus.
                • You need to find a sequence of keys in the campus buildings.
                • The game starts when you enter one of the buildings.
                • You need to be next to objects to check if key exists
                • There is a timeout until you can find the keys
                • You can find hints around the campus map
                • When you find the key the building will be marked as completed
                • You fail if you can not find the key in the given time limit
                • If you manage to find all the keys you win the game !!!!!
                                
                GAME NAVIGATION
                                
                • You can use the arrow keys to move. You can go east, west, north and south. You can not go through walls. You can open the exit door of a building if you find the keys
                • Your aim is to travel the building in the given order: CASE building, SOS building, SCI building, ENG building and SNA building finding keys one by one
                • To find the keys, you use a left click on the object on the building. If a key is present it appears for a second and the door is unlocked.
                • You need to be next to objects to check if key exists
                                
                                
                POWER-UPS
                                
                • You can use power-ups to escape aliens and find keys easily.
                • The power ups appear randomly in every 12 seconds in a random location.
                • Power-up disappear if you not collect them in 6 seconds. To collect power-ups you can right click with mouse on them.
                • You are not required to be next to power-ups to collect them.
                • Other than extra time and extra life power-ups, power-ups can be stored in your bag for later use.
                • Extra time power-up adds extra time to your timer.
                                
                EXTRA TIME POWER-UP
                                
                • The time limit for each object is 5 seconds. If there are 5 objects there is a 25 second time limit for that building. When you collect a Extra Time Power-up you gain 5 extra seconds\s
                                
                                
                HINT
                                
                • Hint power-up gives information about the location of the key to you. Once you collect it is held in your bag.
                • You can use it as soon as you get it or you can save it for next levels.
                • You can use Hint power-up by pressing H on your keyboard. Then, the key is highlighted on the map and you have 1 second before it dissapears.
                                
                PROTECTION VEST
                                
                • Protection Vest power-up protects you from being shot by the shooter alien.\s
                • To use Protection Vest power-up you can use P on your keyboard.\s
                • You can use it as soon as you get it or you can save it for next levels. When you activate it its protection last 20 seconds
                                

                """;

        // Initialize components
        var titleLabel = new JLabel(ThemeManager.getTitle("Help"));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        mainColumn.add(titleLabel);

        var helpTextArea = new JTextArea(helpText);
        helpTextArea.setEditable(false);
        // TODO: Remove magic numbers
        helpTextArea.append(helpText);
        helpTextArea.setFont(new Font("Arial", Font.PLAIN, 10));
        mainColumn.add(helpTextArea);

        var backButton = new JButton("Back");
        // TODO: Remove magic numbers
        backButton.setPreferredSize(new Dimension(20, 60));
        backButton.setForeground(Color.BLUE);
        mainColumn.add(backButton);
    }


    public static void main(String[] args) {
        ScreenManager.getInstance().launch(
                Constants.FRAME_WIDTH,
                Constants.FRAME_HEIGHT,
                Constants.FRAME_TITLE,
                ScreenFactory.getScreen(ScreenType.HELP)
        );
    }
}
