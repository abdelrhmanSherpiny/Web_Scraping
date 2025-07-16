import math
def print_formatted(number):
    sp = int(math.log2(number)) + 1
    for i in range (1, number+1):
        print(f"{i}".rjust(sp), end=" ")
        print(f"{i:o}".rjust(sp), end=" ")
        print(f"{i:X}".rjust(sp), end=" ")
        print(f"{i:b}".rjust(sp))

if __name__ == '__main__':
    n = int(input())
    print_formatted(n)