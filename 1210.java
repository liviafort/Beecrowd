import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class State {
        long totalCost;
        int numReplacements;
        int[] replacements;
        int replacementSize;

        State(long totalCost, int numReplacements, int[] replacements, int replacementSize) {
            this.totalCost = totalCost;
            this.numReplacements = numReplacements;
            this.replacements = replacements;
            this.replacementSize = replacementSize;
        }

        State copyWithReplacement(int year) {
            int[] newReplacements = Arrays.copyOf(this.replacements, this.replacementSize + 1);
            newReplacements[this.replacementSize] = year;
            return new State(this.totalCost, this.numReplacements + 1, newReplacements, this.replacementSize + 1);
        }

        int compareReplacements(State other) {
            int len = Math.min(this.replacementSize, other.replacementSize);
            for (int i = 0; i < len; i++) {
                if (this.replacements[i] != other.replacements[i]) {
                    return Integer.compare(this.replacements[i], other.replacements[i]);
                }
            }
            return Integer.compare(this.replacementSize, other.replacementSize);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    public void run() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            int I = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            int[] Ci = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                Ci[i] = Integer.parseInt(st.nextToken());
            }

            int[] Vi = new int[M + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                Vi[i] = Integer.parseInt(st.nextToken());
            }

            long INF = Long.MAX_VALUE / 2;
            State[] prevDP = new State[M + 1];
            State[] currentDP = new State[M + 1];

            prevDP[I] = new State(0, 0, new int[N], 0);

            for (int t = 0; t < N; t++) {
                Arrays.fill(currentDP, null);

                for (int age = 1; age <= M; age++) {
                    if (prevDP[age] == null) continue;
                    State state = prevDP[age];
                    long totalCost = state.totalCost;
                    int numReplacements = state.numReplacements;

                    if (age < M) {
                        int nextAge = age + 1;
                        long newCost = totalCost + Ci[age];

                        if (shouldUpdate(currentDP[nextAge], newCost, numReplacements, state)) {
                            currentDP[nextAge] = new State(newCost, numReplacements, state.replacements, state.replacementSize);
                        }
                    }

                    int sellPrice = (age >= 1) ? Vi[age] : 0;
                    long replaceCost = totalCost + P - sellPrice + Ci[0];
                    int newAge = 1;
                    if (shouldUpdate(currentDP[newAge], replaceCost, numReplacements + 1, state)) {
                        currentDP[newAge] = state.copyWithReplacement(t + 1);
                        currentDP[newAge].totalCost = replaceCost;
                    }
                }

                State[] temp = prevDP;
                prevDP = currentDP;
                currentDP = temp;
            }

            long minCost = INF;
            State finalState = null;
            for (int age = 1; age <= M; age++) {
                State state = prevDP[age];
                if (state == null) continue;

                if (state.totalCost < minCost) {
                    minCost = state.totalCost;
                    finalState = state;
                } else if (state.totalCost == minCost) {
                    if (state.numReplacements > finalState.numReplacements) {
                        finalState = state;
                    } else if (state.numReplacements == finalState.numReplacements) {
                        if (state.compareReplacements(finalState) < 0) {
                            finalState = state;
                        }
                    }
                }
            }
            System.out.println(minCost);

            if (finalState == null || finalState.replacementSize == 0) {
                System.out.println(0);
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < finalState.replacementSize; i++) {
                    if (i > 0) sb.append(" ");
                    sb.append(finalState.replacements[i]);
                }
                System.out.println(sb.toString());
            }
        }
    }

    private static boolean shouldUpdate(State existingState, long newCost, int numReplacements, State newState) {
        if (existingState == null) {
            return true;
        } else if (newCost < existingState.totalCost) {
            return true;
        } else if (newCost == existingState.totalCost) {
            if (numReplacements > existingState.numReplacements) {
                return true;
            } else if (numReplacements == existingState.numReplacements) {
                return newState.compareReplacements(existingState) < 0;
            }
        }
        return false;
    }
}
