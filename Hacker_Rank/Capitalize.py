#!/bin/python3

import math
import os
import random
import re
import sys


# Complete the solve function below.
def solve(s):
    newS = ""
    if s[0].isalpha():
        newS += s[0].upper()
    else:
        newS += s[0]
    for i in range(1, len(s)):
        if (s[i - 1] == " ") and (s[i].isalpha()):
            newS += s[i].upper()
        else:
            newS += s[i]

    return newS


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    result = solve(s)

    fptr.write(result + '\n')

    fptr.close()
