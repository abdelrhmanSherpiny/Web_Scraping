input ()

eSet = set(map(int, input().split()))

input()

fSet = set(map(int, input().split()))

tSet = eSet.union(fSet)

print(len(tSet))
