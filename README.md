# Find-the-number
_Find a number based on a set of rules_

I saw [this repository](https://github.com/colemclark1/Whats-My-Number) on Cole Clark's github and thought this is a fun problem to solve. The rules, as mentioned there, are:

> Between 1 and 1000, there is only 1 number that meets the following criteria:

    
    1. The number has two or more digits.
    2. The number is prime.
    3. The number does NOT contain a 1 or 7 in it.
    4. The sum of all of the digits is less than or equal to 10.
    5. The first two digits add up to be odd.
    6. The second to last digit is even and greater than 1.
    7. The last digit is equal to how many digits are in the number.

I figured the first step would be to understand the requirements, so let's take them step by step:

1. _two or more digits_ => start at ~10~ ~11 due to rule 2~ ~23 due to other rules~ >100 due to rule 7, I'll let the program figure it out
2. _prime_ => great place to use the `Sieve of Eratosthenes` 
3. _no 1 or 7 in it_ => maybe a while loop that checks `/10%1` and `/10%1` while there are still digits in the number
4. _sum of all digits is <11_ => while going through digits at step 3 skip if you reach sum of digits 11
5. _sum of first two digits is odd_ => always remember previous digit, when you reach the end of the number, sum up that digit with the previous digit; also suggests to use a specific solution for rule 4
6. _second to last digit is even_ => test `%2`
6b. _second to last digit is greater than 1_ => add a condition at rule 4 to test `/10 > 1`
7. _last digit is equal to length of number_ => maybe the simplest way to to this is to convert int to String, and check `length()`; would simplify other conditions as well
