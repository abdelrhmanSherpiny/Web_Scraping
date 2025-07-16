from collections import Counter

input()

myList = list(map(int, input().split()))

freq = Counter(myList)

for x in myList:
    if freq[x] == 1 :
        print(x)
        break
