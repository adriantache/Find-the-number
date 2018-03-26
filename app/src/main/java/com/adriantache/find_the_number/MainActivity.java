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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start by finding primes (Rule 2)
        findNonPrimes();
    }

    /**
     * due to rules 2, 3, 4 and 7 we know that the last digit has to be 3, and the sum of the
     * others has to be between 4 and 7; but due to rule 5, it literally has to be 5 or 7;
     * applying rule 3 again, we actually figure out that the only possible numbers are
     * 233, 323, 253, 343, 433 and 523; of course, now rule 6 comes in, and only leaves us with:
     * 323, 343 and 523, at which point we could just test for prime and be done with it
     */
    //find all non-primes using the Sieve of Eratosthenes (Rule 2)
    private void findNonPrimes() {
        boolean[] sieve = new boolean[1001];

        //init first two numbers as non-prime
        sieve[0] = true;
        sieve[1] = true;

        //build up the sieve, marking non-primes = true
        for (int i = 2; i <= (int) Math.sqrt(1000); i++) {
            if (sieve[i]) continue;
            for (int j = (int) java.lang.Math.pow((double) i, 2); j <= 1000; j += i) {
                sieve[j] = true;
            }
        }


        //Rule 1
        //start at 243, due to rules 1, 3, 4, 5, 6, 7
        int startingNumber = 243;

        //was thinking of limiting this to some number, but it led to the comment I made above
        //this method; it seems like thinking about the problem more just leaves a tiny amount
        //of numbers to test for prime; as such, I'm just leaving this to 1000 so the program
        //isn't just testing a few numbers and that's it
        for (int i = startingNumber; i <= 1000; i++) {
            if (sieve[i]) continue;

            //Rule 7, part 1
            if (i % 10 != 3) continue;

            //then go through all the rules and stop if you find the qualifying number
            if (rules(i)) {
                foundNumber(i);
                break;
            }
        }
    }

    //grouped solutions using Strings (Rules 5 - 7)
    private boolean rules(int currentNumber) {
        String workString = String.valueOf(currentNumber);

        //Rule 7, part 2
        if (workString.length() != 3) return false;

        //Rule 3 (Cole's solution, seems more elegant)
        if (workString.contains("1") || workString.contains("7")) return false;

        //Rule 6
        int rule6 = workString.charAt(workString.length() - 2) - '0';
        if (rule6 % 2 != 0 || rule6 == 0) return false;

        //Rule 4 (since we know the last digit can only be 3, this becomes simple)
        if ((workString.charAt(0) - '0' + workString.charAt(1) - '0') > 7) return false;

        //Rule 5
        return (workString.charAt(0) - '0' + workString.charAt(1) - '0') % 2 != 0;
    }

    //display the number we've found
    public void foundNumber(int currentNumber) {
        TextView textView = findViewById(R.id.textView2);
        textView.setText(String.valueOf(currentNumber));
        textView.setTextColor(0xFF388E3C);
    }
}
