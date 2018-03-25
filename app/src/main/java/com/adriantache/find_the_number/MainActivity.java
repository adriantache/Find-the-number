package com.adriantache.find_the_number;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
    boolean numberFound = false;
    //init sieve to check it rapidly
    int[] sieve = new int[1001];
    //rule 1
    //start at 243, due to rules 1, 3, 4, 5, 6, 7
    int currentNumber = 243;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView2);
        updateNumber();

        //start by finding primes
        findPrimes();

        while (!numberFound && currentNumber <= 1000) {
            if (sieve[currentNumber] == 0) {
                updateNumber();
                if (steps5to7()) {
                    updateNumber();
                    if (steps3and4()) {
                        updateNumber();
                        numberFound = true;
                        foundNumber();
                        break;
                    }
                }
            }
            currentNumber++;
        }
    }

    private void findPrimes() {
        //init first two numbers as non-prime
        sieve[0] = 1;
        sieve[1] = 1;

        for (int i = 2; i <= 1000; i++) {
            if (sieve[i] == 1) continue;
            for (int j = 2; j < 1000 / i; j++) {
                sieve[i * j] = 1;
            }
        }
    }

    private boolean steps5to7() {
        String workString = String.valueOf(currentNumber);

        //starting with rule 7
        if (currentNumber % 10 != workString.length()) return false;

        //rule 6
        int rule6 = workString.charAt(workString.length() - 1) - '1';
        if (rule6 % 2 != 0 || rule6 < 2) return false;

        //rule 5
        return workString.charAt(0) - '1' + workString.charAt(1) - '1' % 2 != 0;
    }

    private boolean steps3and4() {
        int sumDigits = 0;
        int workNumber = currentNumber;

        while (workNumber > 0) {
            //step 3
            if (workNumber % 10 == 1 || workNumber % 10 == 7) return false;
            sumDigits += workNumber % 10;
            if (sumDigits > 10) return false;
            workNumber /= 10;
        }

        //step 4
        return sumDigits % 2 != 0;
    }

    private void updateNumber() {
        textView.setText(String.valueOf(currentNumber));

        //todo pause for dramatic effect
    }

    public void foundNumber() {
        textView.setTextColor(0x388E3C);
    }
}
