input ()

eSet = set(map(int, input().split()))

input()

fSet = set(map(int, input().split()))

tSet = eSet.difference(fSet)

tSet.update(fSet.difference(eSet))

print(len(tSet))
