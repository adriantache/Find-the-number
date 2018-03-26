package com.adriantache.find_the_number;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This program resolves the following quiz:
 * <p>
 * Between 1 and 1000, there is only 1 number that meets the following criteria:
 * <p>
 * 1. The number has two or more digits.
 * 2. The number is prime.
 * 3. The number does NOT contain a 1 or 7 in it.
 * 4. The sum of all of the digits is less than or equal to 10.
 * 5. The first two digits add up to be odd.
 * 6. The second to last digit is even and greater than 1.
 * 7. The last digit is equal to how many digits are in the number.
 */

public class MainActivity extends AppCompatActivity {
    TextView textView;
    //init sieve to hold only found primes
    ArrayList<Integer> sieve = new ArrayList<>();
    //Rule 1
    //start at 243, due to rules 1, 3, 4, 5, 6, 7
    int currentNumber = 243;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView2);

        //start by finding primes (Rule 2)
        findNonPrimes();

        //then go through all the rules and stop if you find the qualifying number
        for (int i : sieve) {
            currentNumber = i;
            if (rules3567()) {
                if (rule4()) {
                    foundNumber();
                    break;
                }
            }
        }
    }

    //find all non-primes using the Sieve of Eratosthenes (Rule 2)
    private void findNonPrimes() {
        boolean[] workSieve = new boolean[1001];

        //init first two numbers as non-prime
        workSieve[0] = true;
        workSieve[1] = true;

        //build up the sieve, marking non-primes = true
        for (int i = 2; i <= (int) Math.sqrt(1000); i++) {
            if (workSieve[i]) continue;
            for (int j = (int) java.lang.Math.pow((double) i, 2); j <= 1000; j += i) {
                workSieve[j] = true;
            }
        }

        //stop before 900 due to rules 3 and 4
        for (int i = currentNumber; i <= 820; i++) {
            if (!workSieve[i]) sieve.add(i);
        }
    }

    //grouped solutions using Strings (Rules 5 - 7)
    private boolean rules3567() {
        String workString = String.valueOf(currentNumber);

        //Rule 7
        if (currentNumber % 10 != workString.length()) return false;

        //Rule 3 (Cole's solution, seems more elegant)
        if (workString.contains("1") || workString.contains("7")) return false;

        //Rule 6
        int rule6 = workString.charAt(workString.length() - 2) - '0';
        if (rule6 % 2 != 0 || rule6 < 2) return false;

        //Rule 5
        return (workString.charAt(0) - '0' + workString.charAt(1) - '0') % 2 != 0;
    }

    //Rule 4
    private boolean rule4() {
        int sumDigits = 0;
        int workNumber = currentNumber;

        while (workNumber > 0) {
            sumDigits += workNumber % 10;
            if (sumDigits > 10) return false;
            workNumber /= 10;
        }

        return true;
    }

    //display the number we've found
    public void foundNumber() {
        textView.setText(String.valueOf(currentNumber));
        textView.setTextColor(0xFF388E3C);
    }
}
