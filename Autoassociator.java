public class Autoassociator {
    private int weights[][];
    private int trainingCapacity;

    public Autoassociator(CourseArray courses) {
        weights = new int[courses.length()][courses.length()];
        trainingCapacity = 0;
        for (int i = 0; i < courses.length(); i++) {
            for (int j = 0; j < courses.length(); j++) {
                weights[i][j] = 0;
            }
        }
    }

    public int getTrainingCapacity() {
        return trainingCapacity;
    }

    public void training(int pattern[]) {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights.length; j++) {
                if (i != j) {
                    weights[i][j] += pattern[i] * pattern[j];
                }
            }
        }
    }

    public int unitUpdate(int neurons[]) {
        int index = (int) (Math.random() * neurons.length);
        int activation = 0;
        for (int i = 0; i < neurons.length; i++) {
            activation += weights[index][i] * neurons[i];
        }
        neurons[index] = (activation >= 0) ? 1 : -1;
        return index;
    }

    public void unitUpdate(int neurons[], int index) {
        int activation = 0;
        for (int i = 0; i < neurons.length; i++) {
            activation += weights[index][i] * neurons[i];
        }
        neurons[index] = (activation >= 0) ? 1 : -1;
    }

    public void chainUpdate(int neurons[], int steps) {
        for (int i = 0; i < steps; i++) {
            unitUpdate(neurons);
        }
    }

    public void fullUpdate(int neurons[]) {
        boolean stable = false;
        while (!stable) {
            int[] oldNeurons = neurons.clone();
            chainUpdate(neurons, 1);
            stable = java.util.Arrays.equals(oldNeurons, neurons);
        }
    }
}
