import java.lang.reflect.Array;
import java.util.*;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return x + "-" + y;
    }
}

public class TwoDiff {
    
    /*Finds the pairs with difference m, by checking every possible pair.
      Time complexity is O(n^2), because we iterate in a for from 0 to n-2,
      and in each iteration we start another iteration from i to n-1, where
      n is the length of the array.
      Space complexity is O(n^2), because there can be a case in which all
      the pairs (i,j) in the array satisfy a[i]-a[j]=m.
      This solution is inefficient, because of the time complexity, but it's
      simple to implement. One advantage of this method is that it can work
      with duplicate elements.*/
    public static ArrayList<Pair> naiveSolution(int[] a, int m) {
        ArrayList<Pair> solution = new ArrayList<>();
        
        for(int i = 0; i < a.length - 1; i++) {
            for(int j = i + 1; j < a.length; j++) {
                if((a[i] - a[j] == m) || (a[j] - a[i] == m)) {
                    solution.add(new Pair(i, j));
                }
            }
        }
        return solution;
    }

    /*Finds the pair with difference m efficiently. The time complexity is O(n),
      where n is the length of the array. We use a map to store the position in
      the initial array of each element. We traverse the array using a for loop,
      and we add each element to a set and we check if (a[i] + m) or (a[i] - m)
      is already in the set, if it is then we found a pair.
      Space complexity is O(n^2), because there can be a case in which all
      the pairs (i,j) in the array satisfy a[i]-a[j]=m.
      This solution is efficient because it iterates two times through the array
      so the complexity is linear. The disadvantage of this method is that it
      can't work with duplicate elements, because each key can have one value
      assigned to it.*/
    public static ArrayList<Pair> efficientSolution(int[] a, int m) {
        ArrayList<Pair> solution = new ArrayList<>();
        Map<Integer, Integer> indexes = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        
        for(int i = 0; i < a.length; i++) {
            indexes.put(a[i], i);
        }

        for(int i = 0; i < a.length; i++) {
            if (set.contains(a[i] - m)) {
                solution.add(new Pair(indexes.get(a[i]), indexes.get(a[i] - m)));
            }

            if (set.contains(a[i] + m)) {
                solution.add(new Pair(indexes.get(a[i]), indexes.get(a[i] + m)));
            }

            set.add(a[i]);
        }
        
        return solution;
    }

    public static void main(String[] args) {
        int n, m;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        m = input.nextInt();
        System.out.println(naiveSolution(a, m));
        System.out.println(efficientSolution(a, m));
    }
}