package common;

import java.util.Arrays;

public class Competitor {
    private String name;
    private int[] scores = new int[17];  // Store scores for 17 event

    public Competitor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setScore(String discipline, int score) {
        switch (discipline) {
            // Decathlon Events
            case "100m":
                scores[0] = score;
                break;
            case "400m":
                scores[1] = score;
                break;
            case "1500m":
                scores[2] = score;
                break;
            case "110m Hurdles":
                scores[3] = score;
                break;
            case "Long Jump":
                scores[4] = score;
                break;
            case "High Jump":
                scores[5] = score;
                break;
            case "Pole Vault":
                scores[6] = score;
                break;
            case "Discus Throw":
                scores[7] = score;
                break;
            case "Javelin Throw":
                scores[8] = score;
                break;
            case "Shot Put":
                scores[9] = score;
                break;

            // Heptathlon Events
            case "Hep 100m Hurdles":
                scores[10] = score;
                break;
            case "Hep 200m":
                scores[11] = score;
                break;
            case "Hep 800m":
                scores[12] = score;
                break;
            case "Hep High Jump":
                scores[13] = score;
                break;
            case "Hep Javelin Throw":
                scores[14] = score;
                break;
            case "Hep Long Jump":
                scores[15] = score;
                break;
            case "Hep Shot Put":
                scores[16] = score;
                break;
        }
    }

    public Object[] getRowData() {
        int totalScore = 0;
        for (int score : scores) {
            totalScore += score;
        }

        Object[] rowData = new Object[scores.length + 2];  // Object array
        rowData[0] = name;  // Name is fine

        // Convert int[] scores to Integer[] and then copy to Object[]
        for (int i = 0; i < scores.length; i++) {
            rowData[i + 1] = scores[i];  // Converting int to Integer (auto-boxing)
        }

        rowData[scores.length + 1] = totalScore;  // Total score in the last column

        return rowData;
    }


    public Object[] getDecathlonData() {
        return getDataByRange(0,10);
    }

    public Object[] getHepathlonData() {
        return getDataByRange(10,17);
    }

    public Object[] getDataByRange(int fromInclusive, int toExclusive){
        int totalScore = 0;
        for (int score : Arrays.copyOfRange(scores, fromInclusive, toExclusive)) {
            totalScore += score;
        }
        int length = toExclusive - fromInclusive;
        Object[] rowData = new Object[length + 2];  // Object array
        rowData[0] = name;  // Name is fine

        // Convert int[] scores to Integer[] and then copy to Object[]
        for (int i = 0; i < length; i++) {
            rowData[i + 1] = scores[fromInclusive+i];  // Converting int to Integer (auto-boxing)
        }

        rowData[length + 1] = totalScore;  // Total score in the last column

        return rowData;
    }

}

