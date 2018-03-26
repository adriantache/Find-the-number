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

    1. _two or more digits_ => start at ~10~ ~11 due to rule 2~ ~23 due to other rules~ ~>100 due to rule 7~ >200 due to rule 3, I'll let the program figure it out
    2. _prime_ => great place to use the `Sieve of Eratosthenes` 
    3. _no 1 or 7 in it_ => maybe a while loop that checks `/10%1` and `/10%1` while there are still digits in the number
    4. _sum of all digits is <11_ => while going through digits at step 3 skip if you reach sum of digits 11
    5. _sum of first two digits is odd_ => always remember previous digit, when you reach the end of the number, sum up that digit with the previous digit; also suggests to use a specific solution for rule 4
    6. _second to last digit is even_ => test `%2`
    6b. _second to last digit is greater than 1_ => add a condition at rule 4 to test `/10 > 1`
    7. _last digit is equal to length of number_ => maybe the simplest way to to this is to convert int to String, and check `length()`; would simplify other conditions as well

Thinking about this further, it would make a lot of sense to figure out how computationally complex each step is, in order to find the best order to run them in. 

    1. very simple, will just reflect initial value
    2. checking for primes, recursive, complex
    3. not using a while loop, just testing for it while checking rule 4, simple
    4. somewhat simple, needs some math
    5. simple, with strings
    6. simple, with strings
    6b. simple, with strings
    7. simple, with strings
   
Based on those judgments, I'd start with step 1 as a start value, then steps 5-7, then step 4, then step 3 and finally step 2. However, step 2 would eliminate a lot of values, and we have to process most of them anyway, so I guess I can just start with that. In the future, perhaps I can figure out a way to limit the check for step 2 to a maximum number less than 1000 based on some logic.

Working on the app, I now see it makes a lot of sense to start with the sieve, the formula for which I've updated as per Wikipedia. Then, for any number left in the sieve, we test the other rules (3-7), grouped by whether we're using strings or not. 

I now realise I may have rushed a bit to write the solution, as there's probably a logical error in there. For example, I now see I calculated rule 5 incorrectly, even though I had planned for it. It is late, though. 

Now I notice there are small problems with rule 4 (was checking the wrong thing at the final return statement, plus it should just return true at that point since it's already checked within the while loop), rule 5 (was subtracting char '1' instead of char '0', and was missing some paranthesis when I simplified the return statement) and rule 6 (was subtracting char '1' instead of char '0', and subtracting 1 from `length()` instead of 2 for some reason). Minor things, really, but I guess I was justified since it's Sunday, I was multitasking, and I've fixed them all quite quickly :)

I also took the time to eliminate some useless code. Perhaps tomorrow I'll think a bit more about whether I can find ways to optimize this even further.

And voila! (I got spoiled when I saw the repo so I guess it's only fair I spoil it for you as well)

![](https://i.imgur.com/lrNgDbB.png)

Ok, so it's morning and I just realised, this problem becomes very simple if you think about it enough beforehand. I guess it's lesson learned for me about rushing in to code something before properly figuring out what the rules mean. So, I was looking for a max number to limit my search for primes at, since rule 4 combined with rule 3 pretty much bring us below 900. But, as I mentioned in the new code, due to rules 2, 3, 4 and 7 we know that the last digit has to be 3, and the sum of the others has to be between 4 and 7; but due to rule 5, it literally has to be 5 or 7; applying rule 3 again, we actually figure out that the only possible numbers are 233, 323, 253, 343, 433 and 523; of course, now rule 6 comes in, and only leaves us with: 323, 343 and 523, at which point we could just test for prime and be done with it. And testing for primality for a small amount of numbers might just be as simple as testing divisibility with ints < sqrt of that number, instead of going to all the trouble of computing the sieve and keeping such a large array in memory. 

I've decided to keep the solution only partially optimized, otherwise the entire app would just be testing 323, 343 and 523 for prime.
