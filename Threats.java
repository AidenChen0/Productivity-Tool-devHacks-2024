/**
 * HOW TO USE:
 * Use Threats.getThreat(); to get a random threat.
 */


import java.util.Random;

public class Threats {
    private static String intro = "Work on your assignment ";
    private static String[] threats = {"please :3", "or we will send your info to a certain company that may or may not track your location and IP address.",
                        "now.", "NOW.", "or we brick your pc.", "or we brick your pc :3", ":3", "now.", "🥺", "or you will be hunted down.", 
                        "or your family will be held hostage."};

    public static String getThreat() {
        int randomNum = (int) (Math.random()*threats.length);
        int uppercaseChance = (int) (Math.random()*3); // 1/3 chance to be uppercase.

        String threat = intro + threats[randomNum];

        if (uppercaseChance == 0) {
            threat = threat.toUpperCase();
        }


        return threat;
    }
}
